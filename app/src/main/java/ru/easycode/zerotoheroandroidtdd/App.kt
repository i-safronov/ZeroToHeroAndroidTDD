package ru.easycode.zerotoheroandroidtdd

import android.app.Application

class App: Application() {

    private lateinit var viewModel: MainViewModel
    private lateinit var stateWrapper: StateWrapper

    override fun onCreate() {
        super.onCreate()
        stateWrapper = StateWrapper.Base()
        viewModel = MainViewModel.instance(stateWrapper = stateWrapper).create(MainViewModel::class.java)
    }

    fun vm() = viewModel
    fun stateWrapper() = stateWrapper

}