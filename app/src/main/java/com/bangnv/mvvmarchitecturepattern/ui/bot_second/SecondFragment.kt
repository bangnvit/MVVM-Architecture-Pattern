package com.bangnv.mvvmarchitecturepattern.ui.bot_second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bangnv.mvvmarchitecturepattern.databinding.FragmentSecondBinding
import com.bangnv.mvvmarchitecturepattern.utils.applyWindowInsets
import com.bangnv.mvvmarchitecturepattern.viewmodels.UserViewModel

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding
        get() = _binding
            ?: throw IllegalStateException("Binding should not be accessed when it is null")
    private val userViewModel: UserViewModel by activityViewModels() // scope: activity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        binding.root.applyWindowInsets()
        bindViewModel()
    }

    // Assign viewModel to binding
    private fun bindViewModel() {
        binding.apply {
            viewModel = userViewModel
            lifecycleOwner = viewLifecycleOwner // Only for Data Binding: Set lifecycleOwner
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}