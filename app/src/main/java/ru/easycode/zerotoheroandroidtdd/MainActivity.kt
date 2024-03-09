package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding
import ru.easycode.zerotoheroandroidtdd.databinding.ElementViewBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter = Adapter()
    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        binding.actionButton.setOnClickListener {
            adapter.add(binding.inputEditText.text.toString().trim())
            binding.inputEditText.setText("")
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val toTypedArray = gson.toJson(adapter.items())
        outState.putString(KEY, toTypedArray)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val str = savedInstanceState.getString(KEY)
        if (str?.isNotEmpty() == true) {
            val list = gson.fromJson(str, Array<String>::class.java).toList()
            adapter.replace(list)
        }
    }

    companion object {
        private const val KEY = "key"
    }

}

private class Adapter: RecyclerView.Adapter<Adapter.Holder>() {

    private var items = mutableListOf<String>()

    class Holder(
        private val binding: ElementViewBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun apply(item: String) {
            binding.root.text = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ElementViewBinding.inflate(inflater, parent, false)
        return Holder(binding = binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.apply(item = items[position])
    }

    fun add(item: String) {
        items.add(item)
        notifyDataSetChanged()
    }

    fun replace(list: List<String>) {
        items = list.toMutableList()
        notifyDataSetChanged()
    }

    fun items(): List<String> = items

}