package com.beetlestance.sample.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.map
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.beetlestance.sample.di.workerfactory.NetworkStateWorker
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Tasks @Inject constructor(
    private val workManager: WorkManager
) {

    fun startNetworkStateWork() {
        val request = OneTimeWorkRequest.Builder(NetworkStateWorker::class.java)
            .addTag(NetworkStateWorker.TAG)
            .build()
        workManager.enqueueUniqueWork(NetworkStateWorker.TAG, ExistingWorkPolicy.REPLACE, request)
    }

    fun observeNetworkState(): Flow<WorkInfo?> {
        return workManager.getWorkInfosForUniqueWorkLiveData(NetworkStateWorker.TAG)
            .map { it.lastOrNull() }.asFlow()
    }
}