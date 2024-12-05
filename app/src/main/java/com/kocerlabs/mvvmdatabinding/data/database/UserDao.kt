package com.kocerlabs.mvvmdatabinding.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kocerlabs.mvvmdatabinding.data.database.entity.CURRENT_USER_ID
import com.kocerlabs.mvvmdatabinding.data.database.entity.User

// Data access object
@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(user: User): Long

    @Query("SELECT * FROM user WHERE localId = $CURRENT_USER_ID")
    fun getUser(): LiveData<User>
}