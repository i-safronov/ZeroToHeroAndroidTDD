package ru.easycode.zerotoheroandroidtdd.state

import android.widget.Button
import android.widget.TextView
import java.io.Serializable

interface UiState : Serializable {

    fun apply(textView: TextView, button: Button)

    abstract class UiStateUtil(private val text: String) : UiState {
        override fun apply(textView: TextView, button: Button) {
            textView.text = text
        }
    }

    data class Base(private val text: String) : UiStateUtil(text = text), UiState

    data class Max(private val text: String) : UiStateUtil(text = text), UiState {
        override fun apply(textView: TextView, button: Button) {
            super.apply(textView, button)
            button.isEnabled = false
        }
    }

}
