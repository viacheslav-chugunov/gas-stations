package com.dev.gasstations.app.presentation.edit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dev.gasstations.R
import com.dev.gasstations.domain.entity.GasStation
import com.dev.gasstations.domain.validator.GasStationValidator
import kotlinx.android.synthetic.main.fragment_edit.*


class EditFragment : Fragment() {

    private var visits = 0
    private var liters = 0.0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit, container, false)
    }

    fun fill(gasStation: GasStation) {
        address.setText(gasStation.address)
        supplier.setText(gasStation.supplier)
        fuelType.setText(gasStation.type)
        fuelCount.setText(gasStation.count.toString())
        fuelCost.setText("%.2f".format(gasStation.cost))
        visits = gasStation.visits
        liters = gasStation.liters
    }

    fun fillAddress(ad: String) =
        address.setText(ad)

    fun isInputValid() : Boolean {
        val validator = GasStationValidator

        val address = address.text.toString()
        val supplier = supplier.text.toString()
        val type = fuelType.text.toString()
        val isValidStringParams =  validator.isValidStringParams(address, supplier, type)

        val count = fuelCount.text.toString()
        val cost = fuelCost.text.toString()
        val isValidNumericParams = validator.isValidNumericParams(count, cost)

        return isValidStringParams && isValidNumericParams
    }

    fun getGasStation() : GasStation {
        val address = address.text.toString()
        val supplier = supplier.text.toString()
        val type = fuelType.text.toString()
        val count = fuelCount.text.toString().toDouble()
        val cost = fuelCost.text.toString().toDouble()
        return GasStation(address, supplier, type, count, cost, visits, liters)
    }

}