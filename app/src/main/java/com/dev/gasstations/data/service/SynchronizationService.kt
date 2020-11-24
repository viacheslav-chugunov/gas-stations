package com.dev.gasstations.data.service

import androidx.lifecycle.LifecycleService
import com.dev.gasstations.R
import com.dev.gasstations.app.ext.showToast
import com.dev.gasstations.data.repository.GlobalRepository
import com.dev.gasstations.data.repository.LocalRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SynchronizationService : LifecycleService() {

    override fun onCreate() {
        super.onCreate()
        synchronizeDatabases()
    }

    private fun synchronizeDatabases() {
        val localRepository = LocalRepository(this)
        val globalRepository = GlobalRepository()

        CoroutineScope(Dispatchers.IO).launch {
            val globalData = globalRepository.getAll()
            val localData = localRepository.getAll()

            println(localData)

            val allData = localData.toMutableList()

            // Waiting while LiveData are loading data from global,
            // cause service have no LifecycleOwner to process it
            Thread.sleep(5000)

            allData += globalData.value ?: emptyList()
            val uniqueData = allData.distinct()
            localRepository.updateAll(uniqueData)
            globalRepository.updateAll(uniqueData)

            withContext(Dispatchers.Main) { showToast(R.string.data_synchronized) }
            stopSelf()
        }
    }

}