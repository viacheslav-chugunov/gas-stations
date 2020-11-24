package com.dev.gasstations.app.presentation.append

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dev.gasstations.R
import kotlinx.android.synthetic.main.fragment_address.*


class AddressFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_address, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adjustSelectButton()
    }

    private fun adjustSelectButton() {
        selectButton.setOnClickListener {
            val parent = activity as? AppendActivity
            val map = childFragmentManager.findFragmentById(R.id.mapFragment) as MapFragment
            parent?.navigateToAppendFragment(map.address)
        }
    }

}