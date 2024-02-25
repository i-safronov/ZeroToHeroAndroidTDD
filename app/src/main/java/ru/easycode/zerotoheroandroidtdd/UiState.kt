package ru.easycode.zerotoheroandroidtdd

import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible

interface UiState {

    fun apply(
        title: TextView,
        button: Button,
        progress: ProgressBar
    )

    data object ShowProgress: UiState {
        override fun apply(title: TextView, button: Button, progress: ProgressBar) {
            progress.isVisible = true
            button.isEnabled = false
        }
    }

    data object ShowData: UiState {
        override fun apply(title: TextView, button: Button, progress: ProgressBar) {
            title.isVisible = true
            progress.isVisible = false
            button.isEnabled = true
        }
    }

}