package com.bangnv.mvvmarchitecturepattern.ui.second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bangnv.mvvmarchitecturepattern.databinding.FragmentSecondBinding
import com.bangnv.mvvmarchitecturepattern.viewmodels.UserViewModel

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding ?: throw IllegalStateException("Binding should not be accessed when it is null")
    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)

        bindViewModel()

        return binding.root
    }

    private fun bindViewModel() {
        // Assign viewModel to binding
        binding.viewModel = userViewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}