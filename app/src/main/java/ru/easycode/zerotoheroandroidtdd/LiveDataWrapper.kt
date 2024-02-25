package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface LiveDataWrapper {

    fun update(value: UiState)
    fun liveData(): LiveData<UiState>

    class Base: LiveDataWrapper {
        private val _state: MutableLiveData<UiState> = MutableLiveData()

        override fun update(value: UiState) {
            _state.postValue(value)
        }

        override fun liveData(): LiveData<UiState> = _state
    }

}