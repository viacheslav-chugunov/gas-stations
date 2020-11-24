package com.dev.gasstations.data.repository

import androidx.lifecycle.MutableLiveData
import com.dev.gasstations.data.firebase.GasStationFirebase
import com.dev.gasstations.domain.entity.GasStation

class GlobalRepository {

    private val db = GasStationFirebase()

    fun getAll() : MutableLiveData<List<GasStation>> =
        db.getAll()

    fun add(gasStation: GasStation) =
        db.add(gasStation)

    fun remove(gasStation: GasStation) =
        db.remove(gasStation)

    fun update(oldGasStation: GasStation, newGasStation: GasStation) {
        db.remove(oldGasStation)
        db.add(newGasStation)
    }

    fun updateAll(gasStations: List<GasStation>) {
        db.removeAll()
        db.addAll(gasStations)
    }

}