package com.beetlestance.sample.di.workerfactory

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WorkerBindingModule {

    @Binds
    @IntoMap
    @WorkerKey(NetworkStateWorker::class)
    fun bindSingleUploadWorker(factory: NetworkStateWorker.NetworkStateWorkerFactory): ChildWorkerFactory
}