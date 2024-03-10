package ru.easycode.zerotoheroandroidtdd.create

import ru.easycode.zerotoheroandroidtdd.core.BaseViewModel
import ru.easycode.zerotoheroandroidtdd.core.ClearViewModel
import ru.easycode.zerotoheroandroidtdd.list.ListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.main.Navigation
import ru.easycode.zerotoheroandroidtdd.main.Screen

class CreateViewModel(
    private val addLiveDataWrapper: ListLiveDataWrapper.Add,
    private val navigation: Navigation.Update,
    private val clearViewModel: ClearViewModel
): BaseViewModel() {

    fun add(text: CharSequence) {
        addLiveDataWrapper.add(source = text)
        comeback()
    }

    fun comeback() {
        navigation.update(Screen.Pop)
        clearViewModel.clear(CreateViewModel::class.java)
    }

    override fun clear() {
        onCleared()
    }

}
