package com.errorclient.filmlist.presentation.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.errorclient.filmlist.R
import com.errorclient.filmlist.data.database.models.FilmWithActorsDataModel

class FilmAdapter(private var filmList: List<FilmWithActorsDataModel>) :
    RecyclerView.Adapter<FilmAdapter.ViewHolder>() {

    /***
     * Создаем свой ClickListener для работы с item по клику из фрагмента
     */
    var onClickListener: ((FilmWithActorsDataModel) -> Unit)? = null

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val nameYearFilm: TextView = view.findViewById(R.id.nameYearFilm)
        val directorName: TextView = view.findViewById(R.id.directorName)
        val actors: TextView = view.findViewById(R.id.actorsField)

        init {
            view.setOnClickListener {
                onClickListener?.invoke(filmList[absoluteAdapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_filmlist, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        /***
         * При прикреплении нового вьюхолдера:
         * парсим информацию о фильме из position элемента списка filmList
         */
        val title = filmList[position].film.title
        val year = filmList[position].film.releaseYear
        val directorName = filmList[position].film.directorName

        /***
         * парсим данные из списка актеров для текущего фильма
         */
        val actorsModel = filmList[position].actors
        val listActor = mutableListOf<String>()
        actorsModel.forEach { listActor.add(it.actorName) }

        /***
         * добавляем указатель на актера
         * собираем актеров в желаемом формате
         */
        val pointer = "☛ "
        val actors = pointer + listActor.joinToString(separator = "\n" + pointer)

        /***
         * заполняем информацию во вьюхолдере
         */
        "$title ($year)".also { viewHolder.nameYearFilm.text = it }
        viewHolder.directorName.text = directorName
        viewHolder.actors.text = actors
    }

    override fun getItemCount() = filmList.size

    /***
     * Функция addItems добавляет новые элементы из списка фильмов в адаптер
     */
    fun addItems(newFilmList: List<FilmWithActorsDataModel>) {

        val diffCallback = ItemDiffUtilCall(this.filmList, newFilmList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        diffResult.dispatchUpdatesTo(this)

        this.filmList = newFilmList
    }

    /***
     * Класс отвечающий за поиск элементов в новом списке фильмов,
     * которые уже были загружены
     */
    private class ItemDiffUtilCall(
        private val oldItemList: List<FilmWithActorsDataModel>,
        private val newItemList: List<FilmWithActorsDataModel>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            return oldItemList.size
        }

        override fun getNewListSize(): Int {
            return newItemList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

            val oldFilmId = oldItemList[oldItemPosition].film.title
            val newFilmId = newItemList[oldItemPosition].film.title

            return oldFilmId == newFilmId
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldFilm = oldItemList[oldItemPosition].film
            val newFilm = newItemList[oldItemPosition].film

            return (
                    oldFilm.releaseYear == newFilm.releaseYear &&
                    oldFilm.directorName == newFilm.directorName
                    )
        }
    }
}
