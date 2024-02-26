package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModelProvider: ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            liveDataWrapper = LiveDataWrapper.Base(),
            repository = Repository.Base()
        ) as T
    }

}