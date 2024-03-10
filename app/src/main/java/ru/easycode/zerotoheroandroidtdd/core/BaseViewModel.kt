package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.ViewModel

abstract class BaseViewModel: ViewModel() {
    abstract fun clear()
}