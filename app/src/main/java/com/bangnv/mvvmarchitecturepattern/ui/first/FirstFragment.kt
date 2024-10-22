package com.bangnv.mvvmarchitecturepattern.ui.first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bangnv.mvvmarchitecturepattern.databinding.FragmentFirstBinding
import com.bangnv.mvvmarchitecturepattern.models.offline.User
import com.bangnv.mvvmarchitecturepattern.utils.GlobalFunction
import com.bangnv.mvvmarchitecturepattern.viewmodels.UserViewModel

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding ?: throw IllegalStateException("Binding should not be accessed when it is null")
    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        bindViewModel()
        observeLoading()
        observeErrorMessage()
        setupCLickListeners()

        return binding.root
    }

    private fun bindViewModel() {
        // Assign viewModel to binding
        binding.viewModel = userViewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun observeLoading() {
        // Observe isLoading LiveData to show/hide ProgressBar
        userViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.prbLoading.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    private fun observeErrorMessage() {
        userViewModel.errorMessage.observe(viewLifecycleOwner) { message ->
            if (message.isNotEmpty()) {
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupCLickListeners() {
        // Get default value
        binding.btnGetFromApi.setOnClickListener {
            userViewModel.fetchUserAPI()

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}