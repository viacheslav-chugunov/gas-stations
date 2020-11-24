package com.dev.gasstations.app.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dev.gasstations.app.presentation.stat.StatFragment
import com.dev.gasstations.app.presentation.store.StoreFragment

class MainViewPagerAdapter(fm: FragmentManager,
                           lifecycle: Lifecycle) : FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> StoreFragment()
            else -> StatFragment()
        }

}