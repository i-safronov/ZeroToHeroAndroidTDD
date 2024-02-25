package ru.easycode.zerotoheroandroidtdd

import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible

interface UiState {

    fun startLoading(button: Button, progressBar: ProgressBar)
    fun loaded(text: TextView, button: Button, progressBar: ProgressBar, content: String = "Hello, android!")

    class Base : UiState {
        override fun startLoading(button: Button, progressBar: ProgressBar) {
            button.isEnabled = false
            progressBar.isVisible = true
        }

        override fun loaded(
            text: TextView,
            button: Button,
            progressBar: ProgressBar,
            content: String
        ) {
            text.text = content
            text.isVisible = true
            button.isEnabled = true
            progressBar.isVisible = false
        }
    }

}