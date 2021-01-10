package com.beetlestance.sample.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.beetlestance.sample.R
import com.beetlestance.sample.databinding.ActivityMainBinding
import com.beetlestance.sample.di.viewmodelfactory.ViewModelFactory
import com.beetlestance.sample.utils.bindWithLifecycleOwner
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(R.layout.activity_main) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: MainActivityViewModel by viewModels { viewModelFactory }

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindWithLifecycleOwner()
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

}