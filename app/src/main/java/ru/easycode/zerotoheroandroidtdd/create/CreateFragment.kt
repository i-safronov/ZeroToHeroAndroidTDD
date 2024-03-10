package ru.easycode.zerotoheroandroidtdd.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.widget.addTextChangedListener
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentCreateBinding


class CreateFragment : AbstractFragment<FragmentCreateBinding>() {

    private lateinit var viewModel: CreateViewModel

    override fun bind(inflater: LayoutInflater, container: ViewGroup?): FragmentCreateBinding =
        FragmentCreateBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val newVM = (activity as ProvideViewModel).viewModel(CreateViewModel::class.java)
        viewModel = (activity as ProvideViewModel).viewModel(CreateViewModel::class.java)

        requireActivity().onBackPressedDispatcher.addCallback {
            viewModel.comeback()
        }

        binding.inputEditText.addTextChangedListener {
            binding.createButton.isEnabled =
                binding.inputEditText.text.toString().length >= MIN_COUNT
        }

        binding.createButton.setOnClickListener {
            hideKeyboard()
            viewModel.add(binding.inputEditText.text.toString().trim())
        }
    }

    companion object {
        private const val MIN_COUNT = 3
    }

}