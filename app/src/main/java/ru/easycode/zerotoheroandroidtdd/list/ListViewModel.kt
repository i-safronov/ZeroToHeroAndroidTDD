package ru.easycode.zerotoheroandroidtdd.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.create.CreateScreen
import ru.easycode.zerotoheroandroidtdd.main.Navigation

class ListViewModel(
    private val liveDataWrapper: ListLiveDataWrapper.Mutable,
    private val navigation: Navigation.Update
): ViewModel(), ListLiveDataWrapper.Read {

    override fun liveData(): LiveData<List<CharSequence>> = liveDataWrapper.liveData()

    fun create() {
        navigation.update(CreateScreen)
    }

}
