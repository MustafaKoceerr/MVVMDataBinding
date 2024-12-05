package com.kocerlabs.mvvmdatabinding.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kocerlabs.mvvmdatabinding.R
import com.kocerlabs.mvvmdatabinding.data.database.AppDatabase
import com.kocerlabs.mvvmdatabinding.data.network.MyApi
import com.kocerlabs.mvvmdatabinding.data.network.model.NetworkConnectionInterceptor
import com.kocerlabs.mvvmdatabinding.data.network.model.UserResponse
import com.kocerlabs.mvvmdatabinding.data.repository.UserRepository
import com.kocerlabs.mvvmdatabinding.databinding.ActivityLoginBinding
import com.kocerlabs.mvvmdatabinding.ui.home.HomeActivity
import com.kocerlabs.mvvmdatabinding.util.snackbar
import com.kocerlabs.mvvmdatabinding.util.visibility

class LoginActivity : AppCompatActivity(), AuthListener {
    val TAG = "LoginActivity"

    private lateinit var viewModel: AuthViewModel
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.root_login)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val networkConnectionInterceptor = NetworkConnectionInterceptor(this)
        val api = MyApi(networkConnectionInterceptor)
        val database = AppDatabase(this)
        val repository = UserRepository(api, database)
        val factory = AuthViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)


        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
//        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        viewModel.authListener = this
        binding.viewmodel = viewModel


        viewModel.getLoggedInUser().observe(this, Observer { user ->
            if (user != null) {
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })

    }


    override fun onStarted() {
        binding.rootLogin.snackbar("Login Started")
        binding.progressBar.visibility(true)
    }

    override fun onSuccess(body: UserResponse?) {
        binding.progressBar.visibility(false)
    }

    override fun onFailure(message: String) {
        binding.rootLogin.snackbar("Error: $message")
        binding.progressBar.visibility(false)
    }
}