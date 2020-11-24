package com.dev.gasstations.app.presentation.store

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.dev.gasstations.R
import com.dev.gasstations.app.adapter.StoreListAdapter
import com.dev.gasstations.app.ext.showToast
import com.dev.gasstations.app.presentation.append.AppendActivity
import com.dev.gasstations.app.presentation.edit.EditActivity
import com.dev.gasstations.app.presentation.main.MainActivity
import com.dev.gasstations.domain.entity.GasStation
import com.dev.gasstations.domain.validator.GasStationValidator
import kotlinx.android.synthetic.main.fragment_store.*


class StoreFragment : Fragment(), StoreListAdapter.Listener {

    private lateinit var viewModel: StoreViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(this).get(StoreViewModel::class.java)
        return inflater.inflate(R.layout.fragment_store, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        adjustAddButton()
    }

    private fun observeData() {
        val list = childFragmentManager.findFragmentById(R.id.list) as StoreListFragment
        viewModel.gasStations.observe(viewLifecycleOwner) {
            list.update(it, this)
        }
    }

    private fun adjustAddButton() {
        addButton.setOnClickListener {
            val intent = Intent(context, AppendActivity::class.java)
            startActivity(intent)
        }
    }

    // StoreListAdapter.Listener Impl
    override fun onClickItem(gasStation: GasStation) {
        val intent = Intent(requireContext(), EditActivity::class.java)
        intent.putExtra(EditActivity.Extra.GAS_STATION, gasStation)
        startActivity(intent)
    }

    override fun onClickVisitButton(gasStation: GasStation) {
        viewModel.updateGasStationForVisits(gasStation)
        updateDataViews()
    }

    override fun onClickEditButton(gasStation: GasStation, litersInput: String) {
        if (GasStationValidator.isValidLitersInput(litersInput)) {
            viewModel.updateGasStationForLiters(gasStation, litersInput.toDouble())
            updateDataViews()
        } else {
            context?.showToast(R.string.invalid_edit_input)
        }
    }

    private fun updateDataViews() {
        val parent = activity as MainActivity
        parent.bindTabLayoutWithViewPager()
        context?.showToast(R.string.note_updated)
    }

}