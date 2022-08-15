package com.errorclient.filmlist.presentation.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.errorclient.filmlist.R
import com.errorclient.filmlist.databinding.FragmentErrorBinding
import com.errorclient.filmlist.databinding.FragmentFilmDialogBinding

class FilmDialogFragment : DialogFragment() {

    private var _binding: FragmentFilmDialogBinding? = null
    private val binding get() = _binding!!
    private val args: FilmDialogFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFilmDialogBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.filmName.text = getString(R.string.format_film_name).format(args.filmName)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}