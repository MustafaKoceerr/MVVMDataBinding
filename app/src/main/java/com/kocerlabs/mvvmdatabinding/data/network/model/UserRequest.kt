package com.kocerlabs.mvvmdatabinding.data.network.model

import com.google.gson.annotations.SerializedName

data class UserRequest(
    @SerializedName("username")
    val email: String? = null,
    @SerializedName("password")
    val password: String? = null
)
