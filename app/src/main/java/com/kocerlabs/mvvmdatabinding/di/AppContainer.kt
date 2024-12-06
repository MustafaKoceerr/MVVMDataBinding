package com.kocerlabs.mvvmdatabinding.di

import android.content.Context
import com.kocerlabs.mvvmdatabinding.data.database.AppDatabase
import com.kocerlabs.mvvmdatabinding.data.network.MyApi
import com.kocerlabs.mvvmdatabinding.data.network.model.NetworkConnectionInterceptor
import com.kocerlabs.mvvmdatabinding.data.repository.UserRepository

class AppContainer(context: Context) {

    private val networkConnectionInterceptor = NetworkConnectionInterceptor(context)
    private val api = MyApi(networkConnectionInterceptor)
    private val database = AppDatabase(context)
    val repository = UserRepository(api, database)


    var authContainer: AuthContainer? = null
}