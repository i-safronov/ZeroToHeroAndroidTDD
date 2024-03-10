package ru.easycode.zerotoheroandroidtdd.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ProvideViewModel {

    private lateinit var binding: ActivityMainBinding
    private lateinit var vm: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        vm = viewModel(MainViewModel::class.java)

        vm.liveData().observe(this) {
            it.show(supportFragmentManager, binding.container.id)
        }

        vm.init(savedInstanceState == null)

    }

    override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T = (application as ProvideViewModel).viewModel(viewModelClass = viewModelClass)

}