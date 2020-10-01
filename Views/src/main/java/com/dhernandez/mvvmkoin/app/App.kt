package com.dhernandez.mvvmkoin.app

import android.app.Application
import com.dhernandez.models.di.MModule
import com.dhernandez.mvvmkoin.di.UIModule
import com.dhernandez.repositories.di.DATAModule
import com.dhernandez.viewmodels.di.VMModule
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {

            modules(arrayListOf(UIModule, VMModule, MModule, DATAModule))

        }

    }

}


