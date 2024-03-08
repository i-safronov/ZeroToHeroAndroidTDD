package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val textState = TextState.Base()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.rootLayout)

        binding.inputEditText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                textState.apply(button = binding.actionButton, char = s)
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.actionButton.setOnClickListener {
            textState.applyTitle(title = binding.titleTextView, button = binding.actionButton, editText = binding.inputEditText)
        }

    }

}

interface TextState {

    fun apply(button: Button, char: CharSequence)
    fun applyTitle(title: TextView, button: Button, editText: TextInputEditText)

    class Base: TextState {

        private var text: CharSequence = ""

        override fun apply(button: Button, char: CharSequence) {
            text = char
            Log.d("777777", "text: $text")
            if (text.toString() == KEY) {
                button.isEnabled = true
            }
        }

        override fun applyTitle(title: TextView, button: Button, editText: TextInputEditText) {
            title.text = text
            button.isEnabled = false
            editText.setText("")
        }

        companion object {
            private const val KEY = "min"
        }

    }

}