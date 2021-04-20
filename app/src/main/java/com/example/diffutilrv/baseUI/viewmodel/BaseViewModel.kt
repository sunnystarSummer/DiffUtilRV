package com.example.diffutilrv.baseUI.viewmodel

import androidx.lifecycle.*

open class BaseViewModel<D> : ViewModel() {
    private var lifecycleOwner: LifecycleOwner? = null

    private fun createMutableLiveData(): MutableLiveData<D> {
        return MutableLiveData()
    }

    private var mutableLiveData: MutableLiveData<D>? = null
        get() {
            if (field == null) field = createMutableLiveData()
            return field
        }

    fun <T : ViewModel?> getViewModel(viewModelStoreOwner: ViewModelStoreOwner?): T {
        val tClass = javaClass as Class<T>
        lifecycleOwner = viewModelStoreOwner as LifecycleOwner?
        return ViewModelProvider(viewModelStoreOwner!!)[tClass]
    }

    fun setValue(data: D) {
        try {
            mutableLiveData?.value = data
        } catch (ignored: IllegalStateException) {
        }
    }

    fun observe(lifecycleOwner: LifecycleOwner?, observer: Observer<D>?) {
        if (lifecycleOwner != null && mutableLiveData != null) mutableLiveData!!.observe(
            lifecycleOwner,
            observer!!
        )
    }
}