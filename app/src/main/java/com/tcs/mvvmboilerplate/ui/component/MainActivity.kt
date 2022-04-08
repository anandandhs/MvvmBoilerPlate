package com.tcs.mvvmboilerplate.ui.component

import android.view.LayoutInflater
import com.tcs.mvvmboilerplate.databinding.ActivityMainBinding
import com.tcs.mvvmboilerplate.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding =
        ActivityMainBinding::inflate

}