package com.allever.app.jetpack

import androidx.room.*

@Dao
interface UserDao {

    @Insert
    fun insert(user: User): Long

    @Update
    fun update(user: User)

    @Query("select * from User")
    fun listAll(): List<User>

    @Query("select * from User where age > :age")
    fun listUserOlderThan(age: Int): List<User>

    @Delete
    fun delete(user: User)

    @Query("delete from User where lastName = :lastName")
    fun deleteByLastName(lastName: String)

}