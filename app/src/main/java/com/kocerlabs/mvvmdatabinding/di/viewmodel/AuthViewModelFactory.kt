package com.kocerlabs.mvvmdatabinding.di.viewmodel

import com.kocerlabs.mvvmdatabinding.data.repository.UserRepository
import com.kocerlabs.mvvmdatabinding.ui.auth.AuthViewModel

class AuthViewModelFactory(
    private val repository: UserRepository
) : Factory<AuthViewModel> {
    override fun create(): AuthViewModel {

        return AuthViewModel(repository)
    }


}