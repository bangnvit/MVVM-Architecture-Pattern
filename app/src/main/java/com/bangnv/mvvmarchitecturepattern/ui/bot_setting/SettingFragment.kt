package com.bangnv.mvvmarchitecturepattern.ui.bot_setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bangnv.mvvmarchitecturepattern.databinding.FragmentSettingBinding
import com.bangnv.mvvmarchitecturepattern.utils.applyWindowInsets

class SettingFragment : Fragment() {

    private var _binding: FragmentSettingBinding? = null
    private val binding
        get() = _binding
            ?: throw IllegalStateException("Binding should not be accessed when it is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupClickListener()
    }

    private fun setupUI() {
        binding.root.applyWindowInsets()
    }

    private fun setupClickListener() {
        binding.btnLanguage.setOnClickListener {
            navigateToLanguageSetting()
        }
    }

    private fun navigateToLanguageSetting() {
        val valueText = binding.tvValue.text.toString()
        val action = SettingFragmentDirections.actionNavSettingToFragLanguage(valueText)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}