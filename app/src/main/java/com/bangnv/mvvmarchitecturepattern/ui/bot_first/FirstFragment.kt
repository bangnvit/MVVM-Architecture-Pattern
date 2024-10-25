package com.bangnv.mvvmarchitecturepattern.ui.bot_first

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
import com.bangnv.mvvmarchitecturepattern.utils.applyWindowInsets
import com.bangnv.mvvmarchitecturepattern.viewmodels.UserViewModel

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding
        get() = _binding
            ?: throw IllegalStateException("Binding should not be accessed when it is null")
    private val userViewModel: UserViewModel by activityViewModels() // scope: activity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        observeViewModel()
        setupClickListeners()
    }

    private fun setupUI() {
        binding.root.applyWindowInsets()
        bindViewModel()
    }

    // Assign viewModel to binding
    private fun bindViewModel() {
        binding.apply {
            viewModel = userViewModel
            lifecycleOwner = viewLifecycleOwner
        }
    }

    private fun observeViewModel() {
        userViewModel.apply {
            // Observe isLoading LiveData to show/hide ProgressBar
            isLoading.observe(viewLifecycleOwner) { isLoading ->
                binding.prbLoading.visibility = if (isLoading) View.VISIBLE else View.GONE
            }
            // When error occurs, show error message
            errorMessage.observe(viewLifecycleOwner) { message ->
                if (message.isNotEmpty()) {
                    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setupClickListeners() {
        binding.apply {
            btnGetFromApi.setOnClickListener { onFetchUserApiClick() }  // Get value from API
            btnUpdateValue.setOnClickListener { onUpdateUserClick() }   // Update data
        }
    }

    private fun onFetchUserApiClick() {
        userViewModel.fetchUserAPI()
        clearInputFields()
    }

    private fun onUpdateUserClick() {
        val username = ObservableField(binding.edtName.text.toString())
        val email = ObservableField(binding.edtEmail.text.toString())
        userViewModel.updateUserData(User(username, email))
        hideKeyboardAndClearFocus()
    }

    private fun clearInputFields() {
        binding.edtName.text.clear()
        binding.edtEmail.text.clear()
        hideKeyboardAndClearFocus()
    }

    private fun hideKeyboardAndClearFocus() {
        GlobalFunction.hideSoftKeyboard(requireActivity())
        binding.edtName.clearFocus()
        binding.edtEmail.clearFocus()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}