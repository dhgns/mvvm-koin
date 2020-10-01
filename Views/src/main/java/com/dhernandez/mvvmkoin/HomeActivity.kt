package com.dhernandez.mvvmkoin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dhernandez.viewmodels.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        loadFragments()

    }

    private fun loadFragments() {

        supportFragmentManager.beginTransaction()
            .add(R.id.listContainerLL,ListFragment())
            .add(R.id.detailContainerRL, DetailFragment())
            .addToBackStack(null)
            .commit()

    }

}

