package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.ViewModel

interface ViewModelFactory: ProvideViewModel, ClearViewModel  {

    class Base(
        private val provideViewModel: ProvideViewModel
    ) : ViewModelFactory {

        private val map = mutableMapOf<Class<out ViewModel>, BaseViewModel>()

        override fun <T : BaseViewModel> viewModel(viewModelClass: Class<T>): T {
            return if (map.containsKey(viewModelClass))
                map[viewModelClass] as T
            else {
                val viewModel = provideViewModel.viewModel(viewModelClass = viewModelClass)
                map[viewModelClass] = viewModel
                viewModel
            }
        }

        override fun clear(viewModelClass: Class<out ViewModel>) {
            map[viewModelClass]?.clear()
            map.remove(viewModelClass)
        }

    }

}
