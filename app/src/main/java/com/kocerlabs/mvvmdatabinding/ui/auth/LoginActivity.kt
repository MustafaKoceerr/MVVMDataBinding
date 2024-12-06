package com.kocerlabs.mvvmdatabinding.ui.auth

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kocerlabs.mvvmdatabinding.R
import com.kocerlabs.mvvmdatabinding.di.AppContainer
import com.kocerlabs.mvvmdatabinding.di.AuthContainer
import com.kocerlabs.mvvmdatabinding.di.MyApplication

class LoginActivity : AppCompatActivity() {
    val TAG = "LoginActivity"

    lateinit var appContainer: AppContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        appContainer = (application as MyApplication).appContainer
        appContainer.authContainer = AuthContainer(appContainer.repository)

        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.root_login)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        appContainer.authContainer = null
    }
}