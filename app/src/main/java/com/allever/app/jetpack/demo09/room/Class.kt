package com.allever.app.jetpack.demo09.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Class(val name: String) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}