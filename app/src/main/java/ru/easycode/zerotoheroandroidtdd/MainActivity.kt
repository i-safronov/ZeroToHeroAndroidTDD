package ru.easycode.zerotoheroandroidtdd

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {

    private lateinit var text: TextView
    private lateinit var progress: ProgressBar
    private lateinit var button: Button

    private val uiState: UiState = UiState.Base()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text = findViewById(R.id.titleTextView)
        progress = findViewById(R.id.progressBar)
        button = findViewById(R.id.actionButton)

        button.setOnClickListener {
            uiState.startLoading(button, progress)
            button.postDelayed({
                uiState.loaded(text, button, progress)
            }, 3000)
        }

    }

}