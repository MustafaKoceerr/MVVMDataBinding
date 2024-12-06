package com.kocerlabs.mvvmdatabinding.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.kocerlabs.mvvmdatabinding.data.network.model.UserRequest
import com.kocerlabs.mvvmdatabinding.data.repository.UserRepository
import com.kocerlabs.mvvmdatabinding.util.ApiException
import com.kocerlabs.mvvmdatabinding.util.Coroutines
import com.kocerlabs.mvvmdatabinding.util.NoInternetException
import com.kocerlabs.mvvmdatabinding.util.toUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    val TAG = "AuthViewModel"
    var email: String? = null
    var password: String? = null
    var authListener: AuthListener? = null


    fun onLoginButtonClick(view: View) {
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            // error message
            authListener?.onFailure("Invalid email or password")
            return
        }
        // success
        Coroutines.main {
            try {
                val response = repository.userLogin(UserRequest(email, password))
                authListener?.onSuccess(response)

                repository.saveUser(response.toUser())
            } catch (e: ApiException) {
                authListener?.onFailure("${e.message}")
            } catch (e: NoInternetException) {
                authListener?.onFailure("${e.message}")
            }
        }
    }


    fun getLoggedInUser() = repository.getUser()

}