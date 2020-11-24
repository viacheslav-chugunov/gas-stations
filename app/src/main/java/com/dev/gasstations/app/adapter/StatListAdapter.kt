package com.dev.gasstations.app.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dev.gasstations.R
import com.dev.gasstations.domain.entity.GasStation


class StatListAdapter(
    private val gasStations: List<GasStation>,
    private val type: ShowType
) : RecyclerView.Adapter<StatListAdapter.ViewHolder>() {


    enum class ShowType { VISITS, LITERS }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_stat, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val gs = gasStations[position]
        holder.run {
            address.text = gs.address
            stat.text = when (type) {
                ShowType.VISITS -> gs.visits.toString()
                ShowType.LITERS -> "%.2f".format(gs.liters)
            }
        }
    }

    override fun getItemCount(): Int = gasStations.count()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val address: TextView = view.findViewById(R.id.address)
        val stat: TextView = view.findViewById(R.id.stat)
    }
}