package ru.easycode.zerotoheroandroidtdd

import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible

interface UiState {

    fun apply(
        text: TextView,
        button: Button,
        progress: ProgressBar
    )

    data object ShowProgress : UiState {
        override fun apply(text: TextView, button: Button, progress: ProgressBar) {
            button.isEnabled = false
            progress.isVisible = true
        }
    }

    data object ShowData : UiState {
        override fun apply(text: TextView, button: Button, progress: ProgressBar) {
            text.isVisible = true
            button.isEnabled = true
            progress.isVisible = false
        }
    }

}