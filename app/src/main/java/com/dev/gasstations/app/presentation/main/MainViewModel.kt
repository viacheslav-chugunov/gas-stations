package com.dev.gasstations.app.presentation.main

import androidx.lifecycle.ViewModel
import androidx.viewpager2.widget.ViewPager2
import com.dev.gasstations.R
import com.dev.gasstations.domain.binder.TabLayoutMediatorBinder
import com.google.android.material.tabs.TabLayout

class MainViewModel : ViewModel() {

    fun bindWithTabMediator(tab: TabLayout, pager: ViewPager2) {
        val titles = listOf(R.string.store, R.string.stats)
        TabLayoutMediatorBinder(tab, pager).bind(titles)
    }

}