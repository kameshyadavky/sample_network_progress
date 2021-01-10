package com.beetlestance.sample.di.modules

import android.content.Context
import androidx.work.Configuration
import androidx.work.WorkManager
import com.beetlestance.sample.SampleApplication
import com.beetlestance.sample.di.scopes.ApplicationContext
import com.beetlestance.sample.di.workerfactory.WorkerFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Singleton
    @Provides
    @ApplicationContext
    fun provideApplicationContext(application: SampleApplication): Context =
        application.applicationContext

    @Singleton
    @Provides
    fun provideWorkManager(@ApplicationContext context: Context): WorkManager =
        WorkManager.getInstance(context)

    @Provides
    fun provideWorkManagerConfiguration(
        workerFactory: WorkerFactory
    ): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
    }
}