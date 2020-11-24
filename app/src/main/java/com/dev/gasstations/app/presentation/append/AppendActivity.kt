package com.dev.gasstations.app.presentation.append

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.dev.gasstations.R
import com.dev.gasstations.app.ext.bindToolbar

class AppendActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_append)
        bindToolbar(title = R.string.append_new, enableUpButton = true)
        navController = Navigation.findNavController(this, R.id.navFragment)
    }

    fun navigateToAppendFragment(address: String) {
        val bundle = Bundle()
        bundle.putString(AppendFragment.Arg.ADDRESS, address)
        navController.navigate(R.id.actionToAppendFragment, bundle)
    }

    fun navigateToAddressFragment() =
        navController.navigate(R.id.actionToAddressFragment)


}