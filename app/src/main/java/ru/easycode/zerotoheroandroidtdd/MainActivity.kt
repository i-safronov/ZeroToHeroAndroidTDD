package ru.easycode.zerotoheroandroidtdd

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), UiWrapper.Events {

    private lateinit var binding: ActivityMainBinding
    private lateinit var vm: MainViewModel
    private lateinit var uiWrapper: UiWrapper.Base

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        vm = (application as App).vm()
        uiWrapper = UiWrapper.Base(vm = vm, events = this, context = this)
        uiWrapper.initList(contentLayout = binding.contentLayout, list = vm.state().value.items)

        binding.actionButton.setOnClickListener {
            uiWrapper.addedNew(contentLayout = binding.contentLayout, editText = binding.inputEditText)
        }

    }

    override fun showMsg(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

}

interface UiWrapper {

    fun initList(contentLayout: ViewGroup, list: List<String>)
    fun addedNew(contentLayout: ViewGroup, editText: EditText)
    fun addView(contentLayout: ViewGroup, value: String)

    class Base(
        private val vm: MainViewModel,
        private val events: Events,
        private val context: Context
    ): UiWrapper {
        override fun initList(contentLayout: ViewGroup, list: List<String>) {
            list.forEach {
                addView(contentLayout = contentLayout, value = it)
            }
        }

        override fun addedNew(contentLayout: ViewGroup, editText: EditText) {
            val text = editText.text.toString().trim()
            if (text.isEmpty()) events.showMsg(msg = "Please, write a message")
            else {
                vm.addNew(value = text)
                addView(contentLayout = contentLayout, value = text)
                editText.setText("")
            }
        }

        override fun addView(contentLayout: ViewGroup, value: String) {
            val view = TextView(context)
            view.text = value
            contentLayout.addView(view)
        }

    }

    interface Events {
        fun showMsg(msg: String)
    }

}