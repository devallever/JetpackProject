package com.allever.app.jetpack.demo03.viewbinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.allever.app.jetpack.databinding.ActivityViewBindingBinding

class ViewBindingActivity: AppCompatActivity() {
    private lateinit var mBinding: ActivityViewBindingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityViewBindingBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mBinding.ivUser
    }
}