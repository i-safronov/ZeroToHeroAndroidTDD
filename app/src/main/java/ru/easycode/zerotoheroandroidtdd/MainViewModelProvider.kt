package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModelProvider: ViewModelProvider.Factory {

    private val liveDataWrapper = LiveDataWrapper.Base()
    private val repository = Repository.Base()

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            liveDataWrapper = liveDataWrapper,
            repository = repository
        ) as T
    }

}