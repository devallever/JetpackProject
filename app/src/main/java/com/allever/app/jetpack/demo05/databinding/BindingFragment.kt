package com.allever.app.jetpack.demo05.databinding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.allever.app.jetpack.R
import com.allever.app.jetpack.databinding.FragmentBindingBinding

class BindingFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentBindingBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_binding, container, false)
        binding.tvBindingFragment.text = "我是Fragment"
        return binding.root
    }
}