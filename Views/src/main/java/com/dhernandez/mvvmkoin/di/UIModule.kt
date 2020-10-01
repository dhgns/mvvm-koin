package com.dhernandez.mvvmkoin.di

import androidx.lifecycle.MutableLiveData
import org.koin.core.qualifier.named
import org.koin.dsl.module

val UIModule = module {

    //Shared Data definitions
    single(named("detail")) { MutableLiveData<String>() }

}

