package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel(
    private val stateWrapper: StateWrapper
): ViewModel(), StateProvider {

    override fun state(): StateFlow<State> = stateWrapper.state()

    fun addNew(value: String) {
        stateWrapper.addNew(value = value)
    }

    companion object {
        fun instance(stateWrapper: StateWrapper): ViewModelProvider.Factory {
            return object: ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return MainViewModel(stateWrapper = stateWrapper) as T
                }
            }
        }
    }

}

data class State(
    val items: List<String>
)

interface StateProvider {
    fun state(): StateFlow<State>
}

interface StateWrapper: StateProvider {

    fun addNew(value: String)

    class Base: StateWrapper {

        private val state = MutableStateFlow(State(items = emptyList()))

        override fun addNew(value: String) {
            val list = state.value.items.toMutableList()
            list.add(value)
            state.value = state.value.copy(
                items = list
            )
        }

        override fun state(): StateFlow<State> = state.asStateFlow()
    }

}