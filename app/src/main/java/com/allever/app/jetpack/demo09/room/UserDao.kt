package com.allever.app.jetpack.demo09.room

import androidx.room.*

@Dao
interface UserDao {
    @Insert
    fun addUser(user: User): Long

    @Delete()
    fun deleteUser(user: User)
    @Query("delete from user where userId = :id")
    fun deleteByUserId(id: Long)
    @Query("delete from user")
    fun deleteAllUser()

    @Update
    fun updateUser(user: User)

    @Query("select * from user")
    fun getAllUser(): List<User>
    @Query("select * from user where userId = :id")
    fun getUserById(id: Long): List<User>
}