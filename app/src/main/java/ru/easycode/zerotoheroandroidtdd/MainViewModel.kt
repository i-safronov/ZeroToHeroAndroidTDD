package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.Serializable

class MainViewModel(
    private val liveDataWrapper: LiveDataWrapper,
    private val repository: Repository
): ViewModel(), LiveDataProperty {

    private val ioScope = CoroutineScope(Dispatchers.IO)

    fun load() {
        ioScope.launch {
            liveDataWrapper.update(UiState.ShowProgress)
            repository.load()
            liveDataWrapper.update(UiState.ShowData)
        }
    }

    fun save(bundleWrapper: BundleWrapper.Save) {
        liveDataWrapper.save(bundleWrapper)
    }

    fun restore(bundleWrapper: BundleWrapper.Restore) {
        liveDataWrapper.update(bundleWrapper.restore())
    }

    companion object {
        fun vm(
            liveDataWrapper: LiveDataWrapper,
            repository: Repository
        ): ViewModelProvider.Factory = object: ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MainViewModel(
                    liveDataWrapper = liveDataWrapper,
                    repository = repository
                ) as T
            }
        }
    }

    override fun liveData(): LiveData<UiState> = liveDataWrapper.liveData()

}

interface UiState: Serializable {

    fun apply(progressBar: ProgressBar, title: TextView, button: Button)

    object ShowProgress: UiState {
        override fun apply(progressBar: ProgressBar, title: TextView, button: Button) {
            progressBar.isVisible = true
            title.isVisible = false
            button.isEnabled = false
        }
    }

    object ShowData: UiState {
        override fun apply(progressBar: ProgressBar, title: TextView, button: Button) {
            progressBar.isVisible = false
            title.isVisible = true
            button.isEnabled = true
        }
    }

}

interface BundleWrapper {

    interface Save: BundleWrapper {
        fun save(uiState: UiState)
    }

    interface Restore: BundleWrapper {
        fun restore(): UiState
    }

    interface Mutable : Save, Restore

    class Base(
        private val bundle: Bundle
    ): Mutable {

        override fun save(uiState: UiState) {
            bundle.putSerializable(KEY, uiState)
        }

        override fun restore(): UiState = bundle.getSerializable(KEY) as UiState

        companion object {
            private const val KEY = "key"
        }

    }

}

interface LiveDataProperty {
    fun liveData(): LiveData<UiState>
}

interface LiveDataWrapper: LiveDataProperty {

    fun save(bundleWrapper: BundleWrapper.Save)

    fun update(value: UiState)

    class Base(
        private val liveData: SingleLiveEvent<UiState> = SingleLiveEvent()
    ): LiveDataWrapper {

        override fun save(bundleWrapper: BundleWrapper.Save) {
            liveData.value?.let { bundleWrapper.save(it) }
        }

        override fun update(value: UiState) {
            liveData.postValue(value)
        }

        override fun liveData(): LiveData<UiState> = liveData
    }

}

interface Repository {

    suspend fun load()

    class Base: Repository {
        override suspend fun load() {
            delay(1000)
        }
    }

}