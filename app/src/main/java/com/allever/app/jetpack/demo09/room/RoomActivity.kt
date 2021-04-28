package com.allever.app.jetpack.demo09.room

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.allever.app.jetpack.R
import com.allever.app.jetpack.ext.log
import kotlinx.coroutines.*

class RoomActivity : AppCompatActivity() {
    private val mJob = Job()
    private val mCoroutine = CoroutineScope(Dispatchers.Main + mJob)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_demo_09_room)

        mCoroutine.launch {
            testDb()
        }
    }

    private suspend fun testDb() {
        val userDao = AppDB.getIns().userDao()
        withContext(Dispatchers.IO) {
            var addedId = userDao.addUser(User(name = "Allever002", age = 25))
            log("addId = $addedId")
            var userList = userDao.getAllUser();
            userList.map {
                log(it.toString())
            }

            userDao.deleteByUserId(2)
            var user = DBController.getUserById(2)
            userList = userDao.getUserById(2)
            if (userList.isNotEmpty()) {
                user = userList[0]
            }
            user.let {

            }
        }
    }
}