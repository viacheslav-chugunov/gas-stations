package com.dev.gasstations.app.presentation.stat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.dev.gasstations.R
import com.dev.gasstations.app.adapter.StatListAdapter.ShowType
import kotlinx.android.synthetic.main.fragment_stat.*
import kotlinx.android.synthetic.main.item_stat.*

class StatFragment : Fragment() {

    private lateinit var viewModel: StatViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(this).get(StatViewModel::class.java)
        return inflater.inflate(R.layout.fragment_stat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        adjustChangeButton()
    }

    private fun observeData() {
        val list = childFragmentManager.findFragmentById(R.id.list) as StatListFragment

        fun updateDisplay(buttonTitle: Int, statTitle: Int) {
            changeButton.setText(buttonTitle)
            stat.setText(statTitle)
        }

        viewModel.type.observe(viewLifecycleOwner) { type ->
            viewModel.gasStations.value?.let { gs ->
                list.update(gs, type)
                if (type == ShowType.LITERS)
                    updateDisplay(R.string.show_visits, R.string.liters)
                else
                    updateDisplay(R.string.show_liters, R.string.visits)
            }
        }

        viewModel.gasStations.observe(viewLifecycleOwner) { gs ->
            viewModel.type.value?.let { type ->
                list.update(gs, type)
            }
        }
    }

    private fun adjustChangeButton() {
        changeButton.setOnClickListener { viewModel.changeType() }
    }

}