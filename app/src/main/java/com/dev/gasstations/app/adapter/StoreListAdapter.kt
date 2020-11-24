package com.dev.gasstations.app.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.dev.gasstations.R
import com.dev.gasstations.domain.entity.GasStation


class StoreListAdapter(
    private val gasStations: List<GasStation>,
    private val listener: Listener
) : RecyclerView.Adapter<StoreListAdapter.ViewHolder>() {


    interface Listener {
        fun onClickItem(gasStation: GasStation)
        fun onClickVisitButton(gasStation: GasStation)
        fun onClickEditButton(gasStation: GasStation, litersInput: String)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_store, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val gs = gasStations[position]
        holder.run {
            layout.setOnClickListener { listener.onClickItem(gs) }
            supplier.text = gs.supplier
            address.text = gs.address

            visitButton.setOnClickListener { listener.onClickVisitButton(gs) }

            litersButton.setOnClickListener {
                val litersInput = litersEdit.text.toString()
                litersEdit.setText("")
                listener.onClickEditButton(gs, litersInput)
            }
        }
    }

    override fun getItemCount(): Int = gasStations.count()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val layout: LinearLayout = view.findViewById(R.id.layout)
        val supplier: TextView = view.findViewById(R.id.supplier)
        val address: TextView = view.findViewById(R.id.address)
        val visitButton: AppCompatButton = view.findViewById(R.id.visitButton)
        val litersEdit: EditText = view.findViewById(R.id.litersEdit)
        val litersButton: AppCompatButton = view.findViewById(R.id.litersButton)
    }
}