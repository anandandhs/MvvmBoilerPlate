package com.tcs.mvvmboilerplate.ui.component.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.tcs.mvvmboilerplate.databinding.FragmentLoginBinding
import com.tcs.mvvmboilerplate.ui.base.BaseFragment
import com.tcs.mvvmboilerplate.utils.Validations.validateKeyWord
import com.tcs.mvvmboilerplate.utils.Validations.validateUserName

class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding =
        FragmentLoginBinding::inflate

    override fun observeViewModel() {}

    override fun initViewModelObservers() {}

    override fun onViewBindingCreated(savedInstanceState: Bundle?) {
        super.onViewBindingCreated(savedInstanceState)

        binding.btnLogin.setOnClickListener {
            doLogin()
        }

    }

    private fun doLogin() {
        val userName = binding.tvUserName.editText?.text.toString()
        val keyWord = binding.tvKeyWord.editText?.text.toString()
        if (validateUserName(userName).first && validateKeyWord(keyWord).first) {
            binding.tvUserName.error = null
            binding.tvKeyWord.error = null
            navigateToFeedScreen()
        } else if (!validateUserName(userName).first) {
            binding.tvUserName.error = validateUserName(userName).second
        } else if (!validateKeyWord(keyWord).first) {
            binding.tvUserName.error = null
            binding.tvKeyWord.error = validateKeyWord(keyWord).second
        }

    }

    private fun navigateToFeedScreen() {
        val toFeedScreen = LoginFragmentDirections.actionLoginFragmentToFeedFragment()
        findNavController().navigate(toFeedScreen)
    }

}