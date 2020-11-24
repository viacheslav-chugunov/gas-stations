package com.dev.gasstations.data.room.dao

import androidx.room.*
import com.dev.gasstations.domain.entity.GasStation

@Dao
interface GasStationDao {

    @Query("SELECT * FROM gas_station ORDER BY supplier ASC")
    suspend fun getAll() : List<GasStation>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(gasStation: GasStation)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(gasStations: List<GasStation>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(gasStation: GasStation)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAll(gasStations: List<GasStation>)

    @Delete
    suspend fun remove(gasStation: GasStation)

    @Query("DELETE FROM gas_station")
    suspend fun removeAll()

}