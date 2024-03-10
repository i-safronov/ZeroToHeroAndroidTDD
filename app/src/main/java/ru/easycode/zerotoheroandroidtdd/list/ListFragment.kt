package ru.easycode.zerotoheroandroidtdd.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentListBinding

class ListFragment : AbstractFragment<FragmentListBinding>() {

    override fun bind(inflater: LayoutInflater, container: ViewGroup?): FragmentListBinding =
        FragmentListBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val vm = (activity as ProvideViewModel).viewModel(ListViewModel::class.java)
        binding.addButton.setOnClickListener {
            vm.create()
        }
    }

}