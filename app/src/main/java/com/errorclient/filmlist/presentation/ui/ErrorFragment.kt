package com.errorclient.filmlist.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.errorclient.filmlist.R
import com.errorclient.filmlist.presentation.vm.ErrorViewModel
import com.errorclient.filmlist.data.repository.models.StatusLoading
import com.errorclient.filmlist.databinding.FragmentErrorBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ErrorFragment : Fragment() {

    private lateinit var binding: FragmentErrorBinding
    private val errorViewModel: ErrorViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentErrorBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        (requireActivity() as AppCompatActivity).supportActionBar?.hide()

        /***
         * Если кнопка нажата и интернет доступен,
         * то переходим на FilmListFragment и повторяем загрузку.
         * При этом ErrorFragment убираем из backstack
         */
        binding.button.setOnClickListener {
            if (errorViewModel.getInternetStatus()) {
                errorViewModel.setStatus(StatusLoading.Start)
                findNavController().navigate(R.id.action_errorFragment_to_filmListFragment)
            }
        }
    }
}