package com.allever.app.jetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        btnAdd.setOnClickListener {
            mViewModel.counter++
            refreshUI()
        }

        refreshUI()

    }

    private fun refreshUI() {
        tvCounter.text = mViewModel.counter.toString()
    }
}