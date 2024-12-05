package com.kocerlabs.mvvmdatabinding.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kocerlabs.mvvmdatabinding.data.database.entity.User

@Database(
    entities = arrayOf(User::class),
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    // tum DAO'ların için abstract fonksiyonlar oluşturmalısın.

    abstract fun getUserDao(): UserDao

    companion object {
        // create AppDatabase
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "MyDatabase.db"
            ).build()
    }

}