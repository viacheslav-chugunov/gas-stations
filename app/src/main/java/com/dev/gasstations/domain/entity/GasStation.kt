package com.dev.gasstations.domain.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity

@Entity(tableName = "gas_station", primaryKeys = ["address"])
data class GasStation(val address: String,
                      val supplier: String,
                      val type: String,
                      val count: Double,
                      val cost: Double,
                      val visits: Int = 0,
                      val liters: Double = 0.0) : Parcelable {

    constructor() : this("", "", "", 0.0, 0.0)

    // Parcelable Impl
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readDouble()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(address)
        parcel.writeString(supplier)
        parcel.writeString(type)
        parcel.writeDouble(count)
        parcel.writeDouble(cost)
        parcel.writeInt(visits)
        parcel.writeDouble(liters)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GasStation> {
        override fun createFromParcel(parcel: Parcel): GasStation {
            return GasStation(parcel)
        }

        override fun newArray(size: Int): Array<GasStation?> {
            return arrayOfNulls(size)
        }
    }

}