package com.dev.gasstations.app.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.dev.gasstations.R
import com.dev.gasstations.app.adapter.MainViewPagerAdapter
import com.dev.gasstations.app.ext.bindToolbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindToolbar()
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        bindTabLayoutWithViewPager()
    }

    fun bindTabLayoutWithViewPager() {
        viewPager.adapter = MainViewPagerAdapter(supportFragmentManager, lifecycle)
        viewModel.bindWithTabMediator(tabLayout, viewPager)
    }

    // Menu Impl
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.refresh -> { bindTabLayoutWithViewPager(); true }
            else -> super.onOptionsItemSelected(item)
        }
    }

}