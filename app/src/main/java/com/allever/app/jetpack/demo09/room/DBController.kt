package com.allever.app.jetpack.demo09.room

object DBController {
    val userDao by lazy {
        AppDB.getIns().userDao()
    }

    fun getUserById(id: Long): User? {
        val result = userDao.getUserById(id)
        return if (result.isEmpty()) {
            null
        } else {
            result[0]
        }
    }
}