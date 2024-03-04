package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.easycode.zerotoheroandroidtdd.data.service.retrofit.SimpleService
import ru.easycode.zerotoheroandroidtdd.domain.Repository
import java.io.Serializable

class MainViewModel(
    private val liveDataWrapper: LiveDataWrapper = LiveDataWrapper.Base(),
    private val repository: Repository
) : ViewModel(), LiveDataProperty {

    private val scope = CoroutineScope(Dispatchers.IO)

    fun load() {
        scope.launch {
            liveDataWrapper.update(UiState.ShowProgress)
            val response = repository.load()
            liveDataWrapper.update(UiState.ShowData(text = response.text))
        }
    }

    fun save(bundleWrapper: BundleWrapper.Save) {
        liveDataWrapper.save(bundleWrapper = bundleWrapper)
    }

    fun restore(bundleWrapper: BundleWrapper.Restore) {
        liveDataWrapper.update(bundleWrapper.restore())
    }

    override fun liveData(): LiveData<UiState> = liveDataWrapper.liveData()

}

interface UiState : Serializable {

    fun apply(textView: TextView, button: Button, progressBar: ProgressBar)

    object ShowProgress : UiState {
        override fun apply(
            textView: TextView,
            button: Button,
            progressBar: ProgressBar
        ) {
            textView.isVisible = false
            button.isEnabled = false
            progressBar.isVisible = true
        }
    }

    data class ShowData(val text: String) : UiState {
        override fun apply(
            textView: TextView,
            button: Button,
            progressBar: ProgressBar
        ) {
            textView.text = text
            textView.isVisible = true
            button.isEnabled = true
            progressBar.isVisible = false
        }
    }


}

interface LiveDataProperty {
    fun liveData(): LiveData<UiState>
}

interface LiveDataWrapper : LiveDataProperty {

    fun save(bundleWrapper: BundleWrapper.Save)
    fun update(value: UiState)

    class Base(
        private val liveData: SingleLiveEvent<UiState> = SingleLiveEvent()
    ) : LiveDataWrapper {

        override fun save(bundleWrapper: BundleWrapper.Save) {
            liveData.value?.let {
                bundleWrapper.save(it)
            }
        }

        override fun update(value: UiState) {
            liveData.postValue(value)
        }

        override fun liveData(): LiveData<UiState> = liveData

    }

}

interface BundleWrapper {

    interface Save {
        fun save(uiState: UiState)
    }

    interface Restore {
        fun restore(): UiState
    }


    interface Mutable : Save, Restore

    class Base(
        private val bundle: Bundle
    ) : Mutable {

        override fun save(uiState: UiState) {
            bundle.putSerializable(KEY, uiState)
        }

        override fun restore(): UiState {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getSerializable(KEY, UiState::class.java) as UiState
            } else {
                bundle.getSerializable(KEY) as UiState
            }
        }

        companion object {
            private const val KEY = "key"
        }

    }

}