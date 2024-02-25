package ru.easycode.zerotoheroandroidtdd

import android.util.Log
import android.widget.Button
import android.widget.TextView

interface UiState {

    fun apply(increment: Button, text: TextView, decrement: Button)

    data class Min(val text: String): UiState {
        override fun apply(increment: Button, text: TextView, decrement: Button) {
            Log.d("040404", "init")
            text.text = this.text
            decrement.isEnabled = false
        }
    }

    data class Base(val text: String): UiState {
        override fun apply(increment: Button, text: TextView, decrement: Button) {
            Log.d("040404", "base")
            text.text = this.text
            decrement.isEnabled = true
            increment.isEnabled = true
        }
    }

    data class Max(val text: String): UiState {
        override fun apply(increment: Button, text: TextView, decrement: Button) {
            Log.d("040404", "max")
            text.text = this.text
            increment.isEnabled = false
        }
    }

}