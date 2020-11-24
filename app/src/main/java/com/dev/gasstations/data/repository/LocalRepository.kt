package com.dev.gasstations.data.repository

import android.content.Context
import com.dev.gasstations.data.room.db.GasStationDatabase
import com.dev.gasstations.domain.entity.GasStation

class LocalRepository(context: Context) {

    private val dao = GasStationDatabase.get(context).dao()

    suspend fun getAll() : List<GasStation> =
        dao.getAll()

    suspend fun add(gasStation: GasStation) =
        dao.add(gasStation)

    suspend fun remove(gasStation: GasStation) =
        dao.remove(gasStation)

    suspend fun update(gasStation: GasStation) =
        dao.update(gasStation)

    suspend fun update(oldGasStation: GasStation, newGasStation: GasStation) {
        dao.remove(oldGasStation)
        dao.add(newGasStation)
    }

    suspend fun updateAll(gasStations: List<GasStation>) {
        dao.removeAll()
        dao.addAll(gasStations)
    }

}