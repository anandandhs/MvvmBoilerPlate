package com.tcs.mvvmboilerplate.ui.component.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.tcs.mvvmboilerplate.R
import com.tcs.mvvmboilerplate.databinding.FragmentSplashBinding
import com.tcs.mvvmboilerplate.ui.base.BaseFragment
import com.tcs.mvvmboilerplate.utils.Constants.SPLASH_TIME

class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSplashBinding =
        FragmentSplashBinding::inflate

    override fun initViewModelObservers() {

    }

    override fun observeViewModel() {

    }

    override fun onViewBindingCreated(savedInstanceState: Bundle?) {
        super.onViewBindingCreated(savedInstanceState)
        navigateToLoginScreen()
    }

    private fun navigateToLoginScreen() {

        Handler(Looper.getMainLooper()).postDelayed({
            val toLoginScreen = SplashFragmentDirections.actionSplashFragmentToLoginFragment()
            val popSplash = NavOptions.Builder()
                .setPopUpTo(
                    R.id.splashFragment,
                    true
                ).build()
            findNavController().navigate(toLoginScreen, popSplash)
        }, SPLASH_TIME)

    }
}