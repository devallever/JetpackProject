package com.allever.app.jetpack

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mViewModel: MainViewModel
    lateinit var mSp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSp = getPreferences(Context.MODE_PRIVATE)
        val count = mSp.getInt("count", 0)

        mViewModel = ViewModelProvider(this, MainViewModelFactory(count))
            .get(MainViewModel::class.java)

        btnAdd.setOnClickListener {
            mViewModel.counter++
            refreshUI()
        }

        btnClear.setOnClickListener {
            mViewModel.counter = 0
            refreshUI()
        }

        refreshUI()

    }

    override fun onPause() {
        super.onPause()
        mSp.edit().putInt("count", mViewModel.counter).apply()
    }

    private fun refreshUI() {
        tvCounter.text = mViewModel.counter.toString()
    }
}