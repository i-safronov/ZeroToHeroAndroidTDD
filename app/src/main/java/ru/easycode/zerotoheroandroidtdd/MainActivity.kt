package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import ru.easycode.zerotoheroandroidtdd.component.Count
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    private val count: Count = Count.Base()
    private var viewState: ViewState = ViewState.Base()

    private lateinit var countTextView: TextView
    private lateinit var incrementButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        countTextView = findViewById(R.id.countTextView)
        incrementButton = findViewById(R.id.incrementButton)

        incrementButton.setOnClickListener {
            viewState.increment(textView = countTextView, count = count)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(
            KEY, viewState
        )
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        viewState = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            savedInstanceState.getSerializable(KEY, ViewState::class.java) as ViewState
        } else {
            savedInstanceState.getSerializable(KEY) as ViewState
        }
        viewState.apply(textView = countTextView)
    }

    companion object {
        const val KEY = "key"
    }

}

interface ViewState : Serializable {

    fun apply(textView: TextView)
    fun increment(textView: TextView, count: Count)
    fun number(): Int

    class Base : ViewState {

        private var number: Int = 0

        override fun apply(textView: TextView) {
            textView.text = number.toString()
        }

        override fun increment(textView: TextView, count: Count) {
            number = count.increment(number = number.toString()).toInt()
            apply(textView = textView)
        }

        override fun number(): Int = number

    }

}