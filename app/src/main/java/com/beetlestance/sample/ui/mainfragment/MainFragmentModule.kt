package com.beetlestance.sample.ui.mainfragment

import androidx.lifecycle.ViewModel
import com.beetlestance.sample.di.scopes.FragmentScoped
import com.beetlestance.sample.di.viewmodelfactory.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class MainFragmentModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributesMainFragment(): MainFragment

    @Binds
    @IntoMap
    @ViewModelKey(MainFragmentViewModel::class)
    abstract fun bindsMainFragmentViewModel(viewModel: MainFragmentViewModel): ViewModel
}