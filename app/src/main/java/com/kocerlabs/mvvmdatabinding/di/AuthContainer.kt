package com.kocerlabs.mvvmdatabinding.di

import com.kocerlabs.mvvmdatabinding.data.repository.UserRepository


class AuthContainer(
    repository: UserRepository
) {
    val authViewModelFactory = com.kocerlabs.mvvmdatabinding.di.viewmodel.AuthViewModelFactory(repository)

}