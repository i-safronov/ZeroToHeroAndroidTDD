package ru.easycode.zerotoheroandroidtdd.component

import ru.easycode.zerotoheroandroidtdd.state.UiState
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

interface Count {

    fun increment(number: String): UiState
    fun check(number: String): UiState

    class Base(
        private val step: Int, private val max: Int,
        countManager: CountManager = CountManager.Base()
    ) : Count {

        init {
            countManager.checkIsLessThanMax(
                step = step, max = max
            )
        }

        override fun increment(number: String): UiState {
            val value = step + number.toInt()
            return check(value.toString())
        }

        override fun check(number: String): UiState {
            return if (number.toInt() >= max) UiState.Max(text = number) else {
                UiState.Base(
                    text = number
                )
            }
        }

    }

    interface CountManager {
        fun checkIsLessThanMax(step: Int, max: Int)

        class Base : CountManager {
            override fun checkIsLessThanMax(step: Int, max: Int) {
                if (max < 0) throw IllegalStateException("max should be positive, but was $max")
                else if (step > max) throw IllegalStateException("max should be more than step")
                else if (step <= 0) throw IllegalStateException(
                    "step should be positive, but was $step"
                )
            }
        }

    }

}
