package com.kocerlabs.mvvmdatabinding.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

const val CURRENT_USER_ID = 0

@Entity(tableName = "User")
data class User(
    @PrimaryKey(autoGenerate = false)
    val localId: Int = CURRENT_USER_ID,
    @SerializedName("accessToken")
    val accessToken: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("firstName")
    val firstName: String?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("lastName")
    val lastName: String?,
    @SerializedName("refreshToken")
    val refreshToken: String?,
    @SerializedName("username")
    val username: String?
)