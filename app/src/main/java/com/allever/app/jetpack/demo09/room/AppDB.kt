package com.allever.app.jetpack.demo09.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class AppDB : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private var INS: AppDB? = null
        private lateinit var context: Context

        fun init(context: Context) {
            this.context = context.applicationContext
        }

        @Synchronized
        fun getIns(): AppDB {
            INS?.let {
                return it
            }

            return Room.databaseBuilder(
                context.applicationContext,
                AppDB::class.java,
                "app_data"
            )
                .build().apply {
                    INS = this
                }
        }
    }
}