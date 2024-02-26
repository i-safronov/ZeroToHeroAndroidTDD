package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var vm: MainViewModel
    private lateinit var text: TextView
    private lateinit var button: Button
    private lateinit var progress: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text = findViewById(R.id.titleTextView)
        button = findViewById(R.id.actionButton)
        progress = findViewById(R.id.progressBar)

        vm = ViewModelProvider(this, MainViewModelProvider()).get(
            MainViewModel::class.java
        )

        vm.state().observe(this) {
            it.apply(text, button, progress)
        }

        button.setOnClickListener {
            vm.load()
        }

    }

}