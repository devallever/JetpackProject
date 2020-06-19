package com.allever.app.jetpack

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(version = 3, entities = [User::class, Book::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun bookDao(): BookDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                val sql = "create table Book (" +
                        "id integer primary key autoincrement not null, " +
                        "name text not null, " +
                        "pages integer not null)"
                database.execSQL(sql)
            }
        }

        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                val sql = "alter table Book add column author text not null default 'unknown'"
                database.execSQL(sql)
            }
        }

        @Synchronized
        fun getDataBase(context: Context): AppDatabase {
            INSTANCE?.let {
                return it
            }

            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_data"
            )
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                .build().apply {
                    INSTANCE = this
                }
        }
    }

}