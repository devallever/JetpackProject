package com.allever.app.jetpack

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.allever.app.jetpack.ext.logd
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

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

        btnGetUser.setOnClickListener {
            val userId = (0..100).random().toString()
            mViewModel.getUser(userId)
        }

        val userDao = AppDatabase.getDataBase(this).userDao()
        val user1 = User("Allever", "Deng", 20)
        val user2 = User("Winchen", "Deng", 12)
        btnAddUser.setOnClickListener {
            thread {
                user1.id = userDao.insert(user1)
                user2.id = userDao.insert(user2)
            }
        }

        btnUpdateUser.setOnClickListener {
            thread {
                user1.age = 40
                userDao.update(user1)
            }
        }

        btnDeleteUser.setOnClickListener {
            thread {
                userDao.delete(user1)
            }
        }

        btnQueryUser.setOnClickListener {
            thread {
                val listUser = userDao.listAll()
                listUser.map {
                    logd("${it.firstName} ${it.lastName} ${it.age}")
                }
            }
        }

        mViewModel.counter.observe(this, Observer {
            tvCounter.text = it.toString()
        })

        mViewModel.user.observe(this, Observer {
            tvCounter.text = it.firstName
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