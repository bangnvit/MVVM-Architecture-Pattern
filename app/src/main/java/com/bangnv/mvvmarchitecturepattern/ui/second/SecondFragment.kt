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

    // This property is only valid between onCreateView() and onDestroyView().
    private val binding get() = _binding!!

    // Used to get ViewModel scoped to the current Fragment
//    private val userViewModel: UserViewModel by viewModels()
    // Used to get ViewModel scoped to the Activity, shared across multiple Fragments
    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Assign viewModel to binding
        binding.viewModel = userViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}