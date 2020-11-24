package com.dev.gasstations.app.presentation.stat

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dev.gasstations.app.adapter.StatListAdapter.ShowType
import com.dev.gasstations.data.repository.LocalRepository
import com.dev.gasstations.domain.entity.GasStation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StatViewModel(app: Application) : AndroidViewModel(app) {

    val type = MutableLiveData<ShowType>(ShowType.VISITS)
    val gasStations = MutableLiveData<List<GasStation>>()
    private val repository = LocalRepository(app)

    init { getGasStations() }

    private fun getGasStations() {
        viewModelScope.launch(Dispatchers.IO) {
            gasStations.postValue(repository.getAll())
        }
    }

    fun changeType() : ShowType {
        type.value = if (type.value == ShowType.VISITS)
            ShowType.LITERS
        else
            ShowType.VISITS
        return type.value ?: ShowType.VISITS
    }

}