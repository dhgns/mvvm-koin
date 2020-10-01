package com.dhernandez.viewmodels

import android.os.Handler
import androidx.core.os.HandlerCompat.postDelayed
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SplashViewModel : ViewModel() {

    private val goHome = MutableLiveData<Boolean>(false)

    init {
        goToMain()
    }

    private fun goToMain() {

        postDelayed(Handler(), {
            goHome.postValue(true)
        }, null, 1500)

    }

    fun shouldIStayOrShouldIGo(): MutableLiveData<Boolean> {
        return goHome
    }

    fun removeObservers(lifecycleOwner: LifecycleOwner) {
        try {
            goHome.removeObservers(lifecycleOwner)
        } catch (e: Exception) {

        }
    }

}