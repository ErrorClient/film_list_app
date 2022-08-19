package com.errorclient.filmlist.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.errorclient.filmlist.R
import com.errorclient.filmlist.data.repository.models.StatusLoading
import com.errorclient.filmlist.databinding.FragmentFilmlistBinding
import com.errorclient.filmlist.presentation.vm.FilmViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilmListFragment : Fragment() {

    private var _binding: FragmentFilmlistBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var filmAdapter: FilmAdapter
    private val filmViewModel: FilmViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFilmlistBinding.inflate(inflater, container, false)

        /***
         * ActionBar показываем только на FilmListFragment
         */
        findNavController().addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.filmListFragment) {
                (requireActivity() as AppCompatActivity).supportActionBar?.show()
            } else {
                (requireActivity() as AppCompatActivity).supportActionBar?.hide()
            }
        }

        /***
         * По клику на фильм переходим на диалог и отдаем ему Название.
         * Запоминаем скролл.
         */
        filmAdapter =
            FilmAdapter(emptyList(), requireActivity()).apply {
                onClickListener = { film ->
                    val action =
                        FilmListFragmentDirections
                            .actionFilmListFragmentToFilmDialogFragment(film.film.title)
                    findNavController().navigate(action)
                }
                stateRestorationPolicy =
                    RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            }

        recyclerView = binding.recyclerView
            .apply {
                layoutManager = LinearLayoutManager(context)
                adapter = filmAdapter
            }

            return binding.root
    }

    override fun onStart() {
        super.onStart()

        /***
         * Подписываемся на статус загрузки.
         * Если Start,
         * то, при наличии интернета, грузим данные,
         * иначе уходим в ErrorFragment.
         * Если Success,
         * то передаем данные в адаптер
         * и скрываем progressBar.
         * Если Error,
         * то уходим в ErrorFragment.
         * Если Loading,
         * то показываем progressBar.
         * Иначе - ничего не делаем (на случай нового значения в StatusLoading)
         */

        // если у вас код ниже подчеркивается красным, то это не значит, что оно не работает :)
        // в новых корутинах стало сложнее бить на модули,
        // у меня не вышло привести к требуемому виду
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            filmViewModel.status.collect { status ->
                when (status) {
                    StatusLoading.Start -> filmViewModel.addFilm()
                    StatusLoading.Success -> {
                        progressVisible(false)

                        filmViewModel.allFilms.collect { listFilm ->
                            filmAdapter.addItems(listFilm)
                        }
                    }
                    StatusLoading.Error -> toErrorFragment()
                    StatusLoading.Loading -> progressVisible(true)
                    else -> {}
                }
            }
        }
    }

    private fun toErrorFragment() {
        findNavController()
            .navigate(R.id.action_filmListFragment_to_errorFragment)
    }

    private fun progressVisible(isVisible: Boolean) {
        view?.post {
            binding.progressBar.isVisible = isVisible
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
