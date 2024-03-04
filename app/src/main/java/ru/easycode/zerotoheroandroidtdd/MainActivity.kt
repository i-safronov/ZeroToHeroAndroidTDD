package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var vm: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vm = (application as App).vm()

        binding.actionButton.setOnClickListener {
            vm.load()
        }

        vm.liveData().observe(this) {
            it.apply(
                textView = binding.titleTextView,
                button = binding.actionButton,
                progressBar = binding.progressBar
            )
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        vm.save(BundleWrapper.Base(bundle = outState))
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        vm.restore(BundleWrapper.Base(savedInstanceState))
    }

}