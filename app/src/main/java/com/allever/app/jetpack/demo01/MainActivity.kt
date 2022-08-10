package com.allever.app.jetpack.demo01

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.allever.app.jetpack.R
import kotlinx.android.synthetic.main.activity_main_01.*

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_01)

//        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
//        viewModel.getUser()

        viewModel = ViewModelProvider(this, MainViewModelFactory(1)).get(MainViewModel::class.java)
        viewModel.userLiveData.observe(this, Observer {
            tvUserAge.text = "${it.age}"
        })
        viewModel.userLiveData.value = User()
        viewModel.userLiveData.postValue(User())

        btnUpdateUserAge.setOnClickListener {
            viewModel.updateUser(tvUserAge.text.toString().toInt() + 1)
        }

    }
}