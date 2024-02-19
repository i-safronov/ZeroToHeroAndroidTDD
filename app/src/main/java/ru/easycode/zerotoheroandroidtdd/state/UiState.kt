package ru.easycode.zerotoheroandroidtdd.state

import android.widget.Button
import android.widget.TextView

interface UiState {

    val text: String

    fun apply(textView: TextView, button: Button)

    abstract class UiStateUtil : UiState {
        override fun equals(other: Any?): Boolean {
            if (other !is UiState) return false
            return other.text == text
        }

        override fun hashCode(): Int {
            return javaClass.hashCode()
        }
    }

    class Base(override val text: String) : UiStateUtil(), UiState {
        override fun apply(textView: TextView, button: Button) {
            textView.text = text
        }
    }

    class Max(override val text: String) : UiStateUtil(), UiState {
        override fun apply(textView: TextView, button: Button) {
            textView.text = text
            button.isEnabled = false
        }
    }

}
