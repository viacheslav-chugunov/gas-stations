package com.dev.gasstations.app.presentation.edit

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.dev.gasstations.data.repository.GlobalRepository
import com.dev.gasstations.data.repository.LocalRepository
import com.dev.gasstations.domain.entity.GasStation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditViewModel(app: Application) : AndroidViewModel(app) {

    var gasStation: GasStation? = null
    private val globalRepository = GlobalRepository()
    private val localRepository = LocalRepository(app)

    fun receiveGasStationFromExtra(intent: Intent) {
        gasStation = intent.extras?.getParcelable<GasStation>(EditActivity.Extra.GAS_STATION) as? GasStation
    }

    fun editGasStation(newGasStation: GasStation) {
        viewModelScope.launch(Dispatchers.IO) {
            gasStation?.let {
                localRepository.update(it, newGasStation)
                globalRepository.update(it, newGasStation)
            }
        }
    }

    fun deleteGasStation() {
        viewModelScope.launch(Dispatchers.IO) {
            gasStation?.let {
                localRepository.remove(it)
                globalRepository.remove(it)
            }
        }
    }

}