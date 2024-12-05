package com.kocerlabs.mvvmdatabinding.util

import com.kocerlabs.mvvmdatabinding.data.database.entity.CURRENT_USER_ID
import com.kocerlabs.mvvmdatabinding.data.database.entity.User
import com.kocerlabs.mvvmdatabinding.data.network.model.UserResponse


fun UserResponse.toUser(): User {
    return User(
        localId = CURRENT_USER_ID, // Varsayılan değer kullanılır
        accessToken = this.accessToken,
        email = this.email,
        firstName = this.firstName,
        gender = this.gender,
        id = this.id,
        image = this.image,
        lastName = this.lastName,
        refreshToken = this.refreshToken,
        username = this.username
    )
}
