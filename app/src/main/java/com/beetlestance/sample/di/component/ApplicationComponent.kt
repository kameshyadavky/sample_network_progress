package com.beetlestance.sample.di.component

import com.beetlestance.sample.SampleApplication
import com.beetlestance.sample.di.modules.ActivityBindingModule
import com.beetlestance.sample.di.modules.ApplicationModule
import com.beetlestance.sample.di.modules.AssistedModule
import com.beetlestance.sample.di.viewmodelfactory.ViewModelModule
import com.beetlestance.sample.di.workerfactory.WorkerBindingModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AssistedModule::class,
        WorkerBindingModule::class,
        ViewModelModule::class,
        ApplicationModule::class,
        ActivityBindingModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<SampleApplication> {
    @Component.Factory
    abstract class Builder : AndroidInjector.Factory<SampleApplication>
}