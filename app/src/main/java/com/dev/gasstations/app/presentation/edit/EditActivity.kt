package com.dev.gasstations.app.presentation.edit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.dev.gasstations.R
import com.dev.gasstations.app.ext.bindToolbar
import com.dev.gasstations.app.ext.showToast
import com.dev.gasstations.app.ext.startActivityInNewTask
import com.dev.gasstations.app.presentation.main.MainActivity
import com.dev.gasstations.data.service.SynchronizationService
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {


    object Extra { const val GAS_STATION = "gas station" }


    private lateinit var viewModel: EditViewModel
    private lateinit var editFragment: EditFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        bindToolbar(title = R.string.edit, enableUpButton = true)
        viewModel = ViewModelProvider(this).get(EditViewModel::class.java)
        viewModel.receiveGasStationFromExtra(intent)
        bindGasStation()
        adjustDeleteButton()
        adjustEditButton()
    }

    private fun bindGasStation() {
        editFragment = supportFragmentManager.findFragmentById(R.id.editFragment) as EditFragment
        viewModel.gasStation?.let { editFragment.fill(it) }
    }

    private fun adjustDeleteButton() {
        deleteButton.setOnClickListener {
            viewModel.deleteGasStation()
            val intent = Intent(this, MainActivity::class.java)
            startActivityInNewTask(intent)
        }
    }

    private fun adjustEditButton() {
        editButton.setOnClickListener {
            if (editFragment.isInputValid()) {
                val newGasStation = editFragment.getGasStation()
                viewModel.editGasStation(newGasStation)
                val intent = Intent(this, MainActivity::class.java)
                startActivityInNewTask(intent)
            } else {
                showToast(R.string.invalid_edit_input, Toast.LENGTH_SHORT)
            }
        }
    }

}