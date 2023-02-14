package com.example.data.database.room

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.database.DbModelCoinPriceInfo


@Database(entities = [DbModelCoinPriceInfo::class], version = 2, exportSchema = false)
abstract class DatabaseCoins : RoomDatabase() {
    abstract fun getDao(): DatabaseCoinsDao

    companion object {
        private var INSTANCE: DatabaseCoins? = null
        private val LOCK = Any()

        private const val NAME = "name"

        fun getInstance(application: Application): DatabaseCoins {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    application, DatabaseCoins::class.java, NAME).build()
                INSTANCE = db
                return db
            }
        }
    }
}