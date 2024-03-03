package ru.easycode.zerotoheroandroidtdd

import android.annotation.SuppressLint
import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var vm: MainViewModel
    private val liveDataWrapper = LiveDataWrapper.Base()
    private val repository = Repository.Base()
    private lateinit var bundleWrapper: BundleWrapper.Base

    private lateinit var progressBar: ProgressBar
    private lateinit var title: TextView
    private lateinit var button: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vm = MainViewModel.vm(
            liveDataWrapper = liveDataWrapper,
            repository = repository
        ).create(MainViewModel::class.java)

        progressBar = findViewById(R.id.progressBar)
        title = findViewById(R.id.titleTextView)
        button = findViewById(R.id.actionButton)

        button.setOnClickListener {
            vm.load()
        }

        vm.liveData().observe(this) {
            it.apply(
                progressBar = progressBar, title = title, button = button
            )
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        vm.save(BundleWrapper.Base(outState))
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        bundleWrapper = BundleWrapper.Base(savedInstanceState)
        vm.restore(bundleWrapper)
    }

}