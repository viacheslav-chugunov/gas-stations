package com.dev.gasstations.data.firebase

import androidx.lifecycle.MutableLiveData
import com.dev.gasstations.domain.entity.GasStation
import com.google.firebase.database.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GasStationFirebase : ValueEventListener {


    companion object { private const val DB_NAME = "Gas station" }


    private val reference: DatabaseReference by lazy { FirebaseDatabase.getInstance().getReference(DB_NAME) }
    private val data = MutableLiveData<List<GasStation>>()

    init { reference.addValueEventListener(this) }

    fun getAll() : MutableLiveData<List<GasStation>> = data

    fun add(gasStation: GasStation) =
        reference
            .child(gasStation.address.correctAddressPath())
            .setValue(gasStation)

    fun addAll(gasStation: List<GasStation>) =
        gasStation.forEach { add(it) }

    fun remove(gasStation: GasStation) =
        reference
            .child(gasStation.address.correctAddressPath())
            .removeValue()

    fun removeAll() =
        reference.removeValue()

    private fun String.correctAddressPath() : String =
        replace('.', ' ')
            .replace('#', ' ')
            .replace('$', ' ')
            .replace('[', ' ')
            .replace(']', ' ')
            .trim()

    // ValueEventListener
    override fun onDataChange(snapshot: DataSnapshot) {
        val gotData = mutableListOf<GasStation>()
        snapshot.children.forEach { children ->
            val got = children.getValue(GasStation::class.java)
            got?.let { gotData += it }
            CoroutineScope(Dispatchers.IO).launch { data.postValue(gotData) }
        }
    }

    override fun onCancelled(error: DatabaseError) {}

}