package com.allever.app.jetpack.demo05.databinding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.allever.app.jetpack.R
import com.allever.app.jetpack.databinding.FragmentBindingBinding

class BindingFragment : Fragment() {
    private lateinit var viewModel: FragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(FragmentViewModel::class.java)
        val binding: FragmentBindingBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_binding, container, false)
        binding.tvBindingFragment.text = "我是Fragment"
        binding.fragmentViewModel = viewModel
        viewModel.text.value = "hello"
        return binding.root
    }
}