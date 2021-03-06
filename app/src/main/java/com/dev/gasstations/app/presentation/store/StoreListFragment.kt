package com.dev.gasstations.app.presentation.store

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dev.gasstations.R
import com.dev.gasstations.app.adapter.StoreListAdapter
import com.dev.gasstations.domain.entity.GasStation

class StoreListFragment : Fragment() {

    private lateinit var recycler: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        recycler = inflater.inflate(R.layout.fragment_store_list, container, false) as RecyclerView
        recycler.layoutManager = LinearLayoutManager(context)
        return recycler
    }

    fun update(gasStations: List<GasStation>, listener: StoreListAdapter.Listener) {
        recycler.adapter = StoreListAdapter(gasStations, listener)
    }

}