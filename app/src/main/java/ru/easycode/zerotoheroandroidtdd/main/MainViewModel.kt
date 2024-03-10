package ru.easycode.zerotoheroandroidtdd.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.core.BaseViewModel
import ru.easycode.zerotoheroandroidtdd.list.ListScreen

class MainViewModel(
    private val navigation: Navigation.Mutable
): BaseViewModel(), Navigation.Read {

    override fun liveData(): LiveData<Screen> = navigation.liveData()

    fun init(firstRun: Boolean) {
        if (firstRun)
            navigation.update(ListScreen)
    }

    override fun clear() {
        onCleared()
    }

}