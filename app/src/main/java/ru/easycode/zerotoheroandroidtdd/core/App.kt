package ru.easycode.zerotoheroandroidtdd.core

import android.app.Application
import androidx.lifecycle.ViewModel

class App : Application(), ProvideViewModel {

    private lateinit var factory: ViewModelFactory
    private val clear: ClearViewModel = object : ClearViewModel {
        override fun clear(viewModelClass: Class<out ViewModel>) {
            factory.clear(viewModelClass = viewModelClass)
        }
    }

    override fun onCreate() {
        super.onCreate()
        val provideViewModel = ProvideViewModel.Base(clear)
        factory = ViewModelFactory.Base(provideViewModel)
    }

    override fun <T : BaseViewModel> viewModel(viewModelClass: Class<T>): T =
        factory.viewModel(viewModelClass = viewModelClass)

}