package ru.easycode.zerotoheroandroidtdd

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import ru.easycode.zerotoheroandroidtdd.component.Count
import ru.easycode.zerotoheroandroidtdd.state.UiState
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    private lateinit var text: TextView
    private lateinit var button: Button
    private val count = Count.Base(
        step = COUNT_STEP, max = COUNT_MAX
    )
    private var stateWrapper: StateWrapper = StateWrapper.Base(count = count)

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text = findViewById(R.id.countTextView)
        button = findViewById(R.id.incrementButton)

        button.setOnClickListener {
            stateWrapper = StateWrapper.Base(count = count)
            stateWrapper.apply(text = text, button = button)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY, stateWrapper)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        stateWrapper = savedInstanceState.getSerializable(KEY) as StateWrapper
        stateWrapper.apply(text = text, button = button)
    }

    companion object {
        private const val COUNT_STEP = 2
        private const val COUNT_MAX = 4
        private const val KEY = "key"
    }

}

interface StateWrapper : Serializable {

    fun apply(text: TextView, button: Button)

    class Base(
        private val count: Count
    ) : StateWrapper {

        override fun apply(text: TextView, button: Button) {
            when (val checkResult = count.check(text.text.toString())) {
                is UiState.Max -> {
                    checkResult.apply(textView = text, button = button)
                }
                is UiState.Base -> {
                    count.increment(text.text.toString())
                        .apply(textView = text, button = button)
                }
            }
        }

    }

}