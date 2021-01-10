package com.beetlestance.sample.ui

import com.beetlestance.sample.di.viewmodelfactory.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainActivityModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindsMainActivityViewModel(viewModel: MainActivityViewModel): MainActivityViewModel
}