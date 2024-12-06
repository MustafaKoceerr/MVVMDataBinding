package com.kocerlabs.mvvmdatabinding.data.repository

import com.kocerlabs.mvvmdatabinding.data.database.AppDatabase
import com.kocerlabs.mvvmdatabinding.data.database.entity.User
import com.kocerlabs.mvvmdatabinding.data.network.MyApi
import com.kocerlabs.mvvmdatabinding.data.network.SafeApiRequest
import com.kocerlabs.mvvmdatabinding.data.network.model.UserRequest
import com.kocerlabs.mvvmdatabinding.data.network.model.UserResponse
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api: MyApi,
    private val database: AppDatabase
) : SafeApiRequest() {

    // ### NETWORK OPERATIONS
    suspend fun userLogin(userRequest: UserRequest): UserResponse =
        apiRequest { api.userLogin(userRequest) }

    // ### DB OPERATIONS
    suspend fun saveUser(user: User) = database.getUserDao().upsert(user)

    fun getUser() = database.getUserDao().getUser()
}