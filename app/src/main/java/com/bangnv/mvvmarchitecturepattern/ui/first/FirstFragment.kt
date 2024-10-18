package com.bangnv.mvvmarchitecturepattern.ui.first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bangnv.mvvmarchitecturepattern.databinding.FragmentFirstBinding
import com.bangnv.mvvmarchitecturepattern.models.User
import com.bangnv.mvvmarchitecturepattern.utils.GlobalFunction
import com.bangnv.mvvmarchitecturepattern.viewmodels.UserViewModel

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

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

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Assign viewModel to binding
        binding.viewModel = userViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        // Get default value
        binding.btnGetDefault.setOnClickListener {
            userViewModel.fetchDefaultUserData()

            GlobalFunction.hideSoftKeyboard(requireActivity())
            binding.edtName.clearFocus()
            binding.edtEmail.clearFocus()
            binding.edtName.setText("")
            binding.edtEmail.setText("")
        }

        // Update data
        binding.btnUpdateValue.setOnClickListener {
            val username = ObservableField(binding.edtName.text.toString())
            val email = ObservableField(binding.edtEmail.text.toString())
            userViewModel.updateUserData(User(username, email))

            GlobalFunction.hideSoftKeyboard(requireActivity())
            binding.edtName.clearFocus()
            binding.edtEmail.clearFocus()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}