package com.dhernandez.mvvmkoin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.dhernandez.viewmodels.SplashViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class SplashActivity : AppCompatActivity() {

    //ViewModel injection by Koin
    private val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        viewModel.shouldIStayOrShouldIGo().observe(this, Observer {

            if (it) {
                startActivity(Intent(this, HomeActivity::class.java))
            }

        })

    }

    override fun onDestroy() {
        super.onDestroy()

        viewModel.removeObservers(this)
    }

}


