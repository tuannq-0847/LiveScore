package com.sun.livescore.ui.scores

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sun.livescore.ui.base.BaseViewModel

class SharedViewModel : BaseViewModel() {
    private val _dateLiveData = MutableLiveData<String>()
    val dateLiveData: LiveData<String>
        get() = _dateLiveData

    fun setDate(date: String) {
        _dateLiveData.value = date
    }
}
