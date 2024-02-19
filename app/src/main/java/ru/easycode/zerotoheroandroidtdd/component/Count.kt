package ru.easycode.zerotoheroandroidtdd.component

import ru.easycode.zerotoheroandroidtdd.state.UiState
import java.lang.IllegalStateException

interface Count {

    fun increment(number: String): UiState

    class Base(
        private val step: Int, private val max: Int,
        countManager: CountManager = CountManager.Base()
    ) : Count {

        init {
            countManager.checkValues(
                step = step, max = max
            )
        }

        override fun increment(number: String): UiState {
            val digits = number.toInt()
            val result = digits + step
            return if (result + step <= max) UiState.Base(text = result.toString()) else {
                UiState.Max(
                    text = result.toString()
                )
            }
        }

    }

    interface CountManager {
        fun checkValues(step: Int, max: Int)

        class Base : CountManager {
            override fun checkValues(step: Int, max: Int) {
                if (max < 0) throw IllegalStateException("max should be positive, but was $max")
                if (step > max) throw IllegalStateException("max should be more than step")
                if (step <= 0) throw IllegalStateException(
                    "step should be positive, but was $step"
                )
            }
        }

    }

}
