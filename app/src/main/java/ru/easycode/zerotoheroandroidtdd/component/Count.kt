package ru.easycode.zerotoheroandroidtdd.component

import java.lang.IllegalStateException

interface Count {

    fun increment(number: String): String

    class Base(
        private var step: Int = 2,
        countState: CountState = CountState.Base()
    ): Count {

        init {
            countState.checkSuitableStep(step = step)
        }

        override fun increment(number: String): String {
            return (step + number.toInt()).toString()
        }

    }

    interface CountState {

        fun checkSuitableStep(step: Int)

        class Base(): CountState {
            override fun checkSuitableStep(step: Int) {
                if (step <= 0) throw IllegalStateException(
                    "step should be positive, but was $step"
                )
            }
        }

    }

}