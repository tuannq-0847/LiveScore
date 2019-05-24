package com.sun.livescore.ui.base

import android.view.View
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {
    open val compositeDisposable by lazy { CompositeDisposable() }
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    open fun eventIsEmpty(list: List<Any>): Boolean = when (list.size) {
        0 -> true
        else -> false
    }

    open fun showVisible(isEmpty: Boolean): Int =
        if (isEmpty) View.VISIBLE else View.GONE
}
