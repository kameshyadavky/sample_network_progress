package com.beetlestance.sample

import androidx.work.Configuration
import com.beetlestance.sample.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Inject

class SampleApplication : DaggerApplication(), Configuration.Provider {

    @Inject
    lateinit var workConfiguration: Configuration

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerApplicationComponent.factory().create(this)

    override fun getWorkManagerConfiguration(): Configuration = workConfiguration

}