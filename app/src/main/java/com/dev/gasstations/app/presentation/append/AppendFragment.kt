package com.dev.gasstations.app.presentation.append

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.dev.gasstations.R
import com.dev.gasstations.app.ext.showToast
import com.dev.gasstations.app.presentation.edit.EditFragment
import com.dev.gasstations.app.presentation.main.MainActivity
import com.dev.gasstations.data.service.SynchronizationService
import kotlinx.android.synthetic.main.fragment_append.*

class AppendFragment : Fragment() {


    object Arg { const val ADDRESS = "address" }


    private lateinit var viewModel: AppendViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(this).get(AppendViewModel::class.java)
        return inflater.inflate(R.layout.fragment_append, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adjustBackButton()
        adjustSaveButton()
        fillAddress()
    }

    private fun adjustBackButton() {
        backButton.setOnClickListener {
            val parent = activity as? AppendActivity
            parent?.navigateToAddressFragment()
        }
    }

    private fun adjustSaveButton() {
        saveButton.setOnClickListener {
            val editFragment = childFragmentManager.findFragmentById(R.id.editFragment) as EditFragment
            if (editFragment.isInputValid()) {
                val gs = editFragment.getGasStation()
                viewModel.addGasStation(gs)

                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent)

                val service = Intent(requireContext(), SynchronizationService::class.java)
                context?.startService(service)
            } else {
                context?.showToast(R.string.invalid_edit_input, Toast.LENGTH_SHORT)
            }
        }
    }

    private fun fillAddress() {
        val address = arguments?.getString(Arg.ADDRESS) ?: ""
        val editFragment = childFragmentManager.findFragmentById(R.id.editFragment) as EditFragment
        editFragment.fillAddress(address)
    }

}