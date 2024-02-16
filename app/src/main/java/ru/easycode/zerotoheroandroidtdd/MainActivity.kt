package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var text: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text = findViewById(R.id.titleTextView)
        val button = findViewById<Button>(R.id.changeButton)

        text.text = savedInstanceState?.getString(TEXT_KEY) ?: "Hello World!"

        button.setOnClickListener {
            text.text = "I am an Android Developer!"
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(TEXT_KEY, this.text.text.toString())
    }

    companion object {
        private const val TEXT_KEY = "text_key"
    }

}