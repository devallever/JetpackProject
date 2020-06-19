package com.allever.app.jetpack

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mViewModel: MainViewModel
    lateinit var mSp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycle.addObserver(MyLifeObserver(lifecycle))

        mSp = getPreferences(Context.MODE_PRIVATE)
        val count = mSp.getInt("count", 0)

        mViewModel = ViewModelProvider(this, MainViewModelFactory(count))
            .get(MainViewModel::class.java)

        btnAdd.setOnClickListener {
            mViewModel.plusOne()
        }

        btnClear.setOnClickListener {
            mViewModel.clear()
        }

        mViewModel.counter.observe(this, Observer {
            tvCounter.text = it.toString()
        })

        refreshUI()

    }

    override fun onPause() {
        super.onPause()
        mSp.edit().putInt("count", mViewModel.counter.value ?: 0).apply()
    }

    private fun refreshUI() {
        tvCounter.text = mViewModel.counter.toString()
    }
}