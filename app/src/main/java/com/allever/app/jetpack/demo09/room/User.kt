package com.allever.app.jetpack.demo09.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    val name: String,
    val age: Int
) {
    @PrimaryKey(autoGenerate = true) var userId: Long = 0

    override fun toString(): String {
        return "User(userId=$userId, name=$name, age=$age)"
    }
}