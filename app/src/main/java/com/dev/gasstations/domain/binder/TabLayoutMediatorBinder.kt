package com.dev.gasstations.domain.binder

import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class TabLayoutMediatorBinder(private val tabLayout: TabLayout, private val viewPager: ViewPager2) {

    fun bind(titles: List<Int>) {
        TabLayoutMediator(tabLayout, viewPager) { tab, pos ->
            val title = titles[pos]
            tab.setText(title)
        }.attach()
    }

}