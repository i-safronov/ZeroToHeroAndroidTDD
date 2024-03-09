package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding
import ru.easycode.zerotoheroandroidtdd.databinding.ElementViewBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var vm: MainViewModel
    private val adapter = Adapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        vm = (application as App).vm()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        binding.actionButton.setOnClickListener {
            vm.add(binding.inputEditText.text.toString())
            binding.inputEditText.setText("")
        }

        vm.liveData().observe(this) {
            adapter.replace(it.map { it.toString() })
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

private class Adapter: RecyclerView.Adapter<Adapter.Holder>() {

    val diffUtil = object: DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
    private val asyncDiffList = AsyncListDiffer(this, diffUtil)

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

    override fun getItemCount(): Int = asyncDiffList.currentList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.apply(item = asyncDiffList.currentList[position])
    }

    fun replace(list: List<String>) {
        asyncDiffList.submitList(list)
    }

}