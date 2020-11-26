package com.allever.app.jetpack.demo02.databinding

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.allever.app.jetpack.R
import com.allever.app.jetpack.databinding.ActivityDataBindingBinding
import kotlinx.android.synthetic.main.activity_data_binding.*

class BindingActivity : AppCompatActivity() {
    private lateinit var mViewModel: BindingViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityDataBindingBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding)
        binding.lifecycleOwner = this
        mViewModel = ViewModelProvider(this).get(BindingViewModel::class.java)
        binding.bindingViewModel = mViewModel
        btnRequest.setOnClickListener {
            val q = etInput.text?.toString() ?: ""
            if (q.isEmpty()) {
                toast("请输入内容")
                return@setOnClickListener
            }
            mViewModel.translate(q)
        }

    }

    private fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}