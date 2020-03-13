package com.zotiko.spacelaunchnow.ui.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        clearSubscriptions()
        super.onCleared()
    }

    fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    private fun clearSubscriptions() {
        disposables.clear()
    }
}