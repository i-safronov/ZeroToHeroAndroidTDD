package ru.easycode.zerotoheroandroidtdd.create

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentCreateBinding

class CreateFragment : AbstractFragment<FragmentCreateBinding>() {
    override fun bind(inflater: LayoutInflater, container: ViewGroup?): FragmentCreateBinding =
        FragmentCreateBinding.inflate(inflater, container, false)
}