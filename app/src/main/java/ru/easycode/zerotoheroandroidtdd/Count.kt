package ru.easycode.zerotoheroandroidtdd

import android.util.Log

interface Count {

    fun initial(number: String?): UiState
    fun increment(number: String): UiState
    fun decrement(number: String): UiState

    class Base(
        private val step: Int = 2,
        private val max: Int = 4,
        private val min: Int = 0
    ) : Count {

        init {
            if (step <= 0) throw IllegalStateException("step should be positive, but was $step")
            else if (max <= 0) throw IllegalStateException("max should be positive, but was $max")
            else if (max < step) throw IllegalStateException("max should be more than step")
        }

        override fun initial(number: String?): UiState {
            val digits = number?.toInt() ?: min
            return findUiState(digits)
        }

        override fun increment(number: String): UiState {
            val digits = number.toInt() + step
            return findUiState(digits)
        }

        override fun decrement(number: String): UiState {
            val digits = number.toInt() - step
            return findUiState(digits)
        }

        private fun findUiState(value: Int): UiState {
            val text = value.toString()
            return if (value <= min) UiState.Min(text)
            else if (value >= max) UiState.Max(text)
            else UiState.Base(text)
        }

    }

}
