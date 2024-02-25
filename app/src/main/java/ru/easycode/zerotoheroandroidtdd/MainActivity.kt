package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var text: TextView
    private lateinit var increment: Button
    private lateinit var decrement: Button

    private val count = Count.Base()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text = findViewById(R.id.countTextView)
        increment = findViewById(R.id.incrementButton)
        decrement = findViewById(R.id.decrementButton)

        val cText =savedInstanceState?.getString(KEY, "0")
        val state = count.initial(cText)

        state.apply(increment, text, decrement)

        increment.setOnClickListener {
            count.increment(text.text.toString()).apply(increment, text, decrement)
        }

        decrement.setOnClickListener {
            count.decrement(text.text.toString()).apply(increment, text, decrement)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY, text.text.toString())
    }

    companion object {
        private const val KEY = "key"
    }

}