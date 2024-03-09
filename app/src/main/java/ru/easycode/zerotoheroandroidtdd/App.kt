package ru.easycode.zerotoheroandroidtdd

import android.app.Application

class App: Application() {

    private lateinit var vm: MainViewModel

    override fun onCreate() {
        super.onCreate()
        vm = MainViewModel(listLiveDataWrapper = ListLiveDataWrapper.Base())
    }

    fun vm() = vm

}