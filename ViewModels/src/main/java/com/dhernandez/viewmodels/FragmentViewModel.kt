package com.dhernandez.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FragmentViewModel(private val detailView: MutableLiveData<String>) : ViewModel() {

    private val details =
        arrayListOf("First Detail", "Second Detail", "Third Detail", "Fourth Detail")

    fun setDetail(item: Int) {
        detailView.value = details[item]
    }

    fun getDetail(): MutableLiveData<String> {
        return detailView
    }


}