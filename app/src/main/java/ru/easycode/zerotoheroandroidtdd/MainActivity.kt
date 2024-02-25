package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var vm: MainViewModel
    private lateinit var title: TextView
    private lateinit var button: Button
    private lateinit var progress: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = findViewById(R.id.titleTextView)
        button = findViewById(R.id.actionButton)
        progress = findViewById(R.id.progressBar)

        vm = ViewModelProvider(this, MainViewModelProvider()).get(
            MainViewModel::class.java
        )

        vm.state().observe(this) {
            Log.d("040404", "observing...")
            it.apply(title, button, progress)
        }

        button.setOnClickListener {
            vm.load()
        }

    }

}