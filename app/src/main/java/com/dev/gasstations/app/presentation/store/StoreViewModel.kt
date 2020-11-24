package com.dev.gasstations.app.presentation.store

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dev.gasstations.data.repository.GlobalRepository
import com.dev.gasstations.data.repository.LocalRepository
import com.dev.gasstations.domain.entity.GasStation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StoreViewModel(app: Application) : AndroidViewModel(app) {

    val gasStations = MutableLiveData<List<GasStation>>()
    private val globalRepository = GlobalRepository()
    private val localRepository = LocalRepository(app)

    init { getGasStations() }

    private fun getGasStations() {
        viewModelScope.launch(Dispatchers.IO) {
            gasStations.postValue(localRepository.getAll())
        }
    }

    fun updateGasStationForVisits(old: GasStation) {
        viewModelScope.launch(Dispatchers.IO) {
            val new = old.copy(visits = old.visits + 1)
            updateGasStation(old, new)
        }
    }

    fun updateGasStationForLiters(old: GasStation, liters: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            val new = old.copy(liters = old.liters + liters)
            updateGasStation(old, new)
        }
    }

    private suspend fun updateGasStation(old: GasStation, new: GasStation) {
        localRepository.update(new)
        globalRepository.update(old, new)
        getGasStations()
    }

}