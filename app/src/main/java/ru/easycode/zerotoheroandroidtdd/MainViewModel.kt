package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainViewModel(
    private val listLiveDataWrapper: ListLiveDataWrapper
): LiveDataProvider {

    fun add(text: CharSequence) {
        listLiveDataWrapper.add(new = text)
    }

    fun save(bundle: BundleWrapper.Save) {
        listLiveDataWrapper.save(bundle)
    }

    fun restore(bundle: BundleWrapper.Restore) {
        listLiveDataWrapper.update(bundle.restore())
    }

    override fun liveData(): LiveData<List<CharSequence>> = listLiveDataWrapper.liveData()

}

interface LiveDataProvider {
    fun liveData(): LiveData<List<CharSequence>>
}

interface ListLiveDataWrapper : LiveDataProvider {

    fun add(new: CharSequence)
    fun save(bundle: BundleWrapper.Save)
    fun update(list: List<CharSequence>)

    class Base(
        private val liveData: MutableLiveData<List<CharSequence>> = SingleLiveEvent()
    ) : ListLiveDataWrapper {
        override fun liveData(): LiveData<List<CharSequence>> = liveData

        override fun add(new: CharSequence) {
            val list = liveData.value?.toMutableList() ?: mutableListOf()
            list.add(new)
            liveData.postValue(list)
        }

        override fun save(bundle: BundleWrapper.Save) {
            bundle.save(ArrayList(liveData.value ?: emptyList()))
        }

        override fun update(list: List<CharSequence>) {
            liveData.postValue(list)
        }
    }

}

interface BundleWrapper {

    interface Save : BundleWrapper {
        fun save(list: ArrayList<CharSequence>)
    }

    interface Restore : BundleWrapper {
        fun restore(): List<CharSequence>
    }

    interface Mutable : Save, Restore

    class Base(
        private val bundle: Bundle
    ) : BundleWrapper.Mutable {
        override fun save(list: ArrayList<CharSequence>) {
            bundle.putCharSequenceArrayList(KEY, list)
        }

        override fun restore(): List<CharSequence> {
            return bundle.getCharSequenceArrayList(KEY)?.toList() ?: emptyList()
        }

        companion object {
            private const val KEY = "safronov.key"
        }
    }
}
