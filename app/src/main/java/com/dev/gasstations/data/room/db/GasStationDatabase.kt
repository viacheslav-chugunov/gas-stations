package com.dev.gasstations.data.room.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dev.gasstations.data.room.dao.GasStationDao
import com.dev.gasstations.domain.entity.GasStation

@Database(entities = [GasStation::class], version = 1)
abstract class GasStationDatabase : RoomDatabase() {


    companion object {
        private const val TABLE_NAME = "gas_station_database"
        private var INSTANCE: GasStationDatabase? = null

        fun get(context: Context) : GasStationDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(context, GasStationDatabase::class.java, TABLE_NAME)
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }


    abstract fun dao() : GasStationDao

}