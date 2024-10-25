package com.bangnv.mvvmarchitecturepattern.ui.testnavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bangnv.mvvmarchitecturepattern.databinding.FragmentLanguageBinding
import com.bangnv.mvvmarchitecturepattern.utils.applyWindowInsets

class LanguageFragment : Fragment() {

    private var _binding: FragmentLanguageBinding? = null
    private val binding
        get() = _binding
            ?: throw IllegalStateException("Binding should not be accessed when it is null")
    private val args: LanguageFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLanguageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        binding.root.applyWindowInsets()
        binding.tvValueReceived.text = args.valueText
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}