package com.beetlestance.sample.di.modules

import com.beetlestance.sample.di.scopes.ActivityScoped
import com.beetlestance.sample.ui.MainActivity
import com.beetlestance.sample.ui.MainActivityModule
import com.beetlestance.sample.ui.mainfragment.MainFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            MainActivityModule::class,
            MainFragmentModule::class
        ]
    )
    abstract fun contributesMainActivity(): MainActivity
}