package com.dev.gasstations.app.presentation.append

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.dev.gasstations.data.repository.LocalRepository
import com.dev.gasstations.domain.entity.GasStation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppendViewModel(app: Application) : AndroidViewModel(app) {

    private val repository = LocalRepository(app)

    fun addGasStation(gasStation: GasStation) {
        viewModelScope.launch(Dispatchers.IO) { repository.add(gasStation) }
    }

}