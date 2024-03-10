package ru.easycode.zerotoheroandroidtdd.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.ElementTextViewBinding
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentListBinding

class ListFragment : AbstractFragment<FragmentListBinding>() {

    private val adapter = Adapter()
    private lateinit var vm: ListViewModel

    override fun bind(inflater: LayoutInflater, container: ViewGroup?): FragmentListBinding =
        FragmentListBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm = (activity as ProvideViewModel).viewModel(ListViewModel::class.java)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        savedInstanceState?.let {
            vm.restore(BundleWrapper.Base(it))
        }

        binding.addButton.setOnClickListener {
            vm.create()
        }

        vm.liveData().observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (this@ListFragment::vm.isInitialized)
            vm.save(BundleWrapper.Base(bundle = outState))
    }

}

class Adapter: RecyclerView.Adapter<Adapter.ViewHolder>() {

    private val currentList = mutableListOf<CharSequence>()

    class ViewHolder(
        private val binding: ElementTextViewBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun apply(item: CharSequence) {
            binding.elementTextView.text = item
        }
    }

    private class DiffUtil(
        private val oldList: List<CharSequence>,
        private val newList: List<CharSequence>
    ): androidx.recyclerview.widget.DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ElementTextViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply(currentList[position])
    }

    fun submitList(list: List<CharSequence>) {
        val diffCallBack = DiffUtil(oldList = currentList, newList = list)
        val diffResult = androidx.recyclerview.widget.DiffUtil.calculateDiff(diffCallBack)

        this@Adapter.currentList.clear()
        this@Adapter.currentList.addAll(list)
        diffResult.dispatchUpdatesTo(this@Adapter)
    }

}