package com.kocerlabs.mvvmdatabinding.ui.auth

import com.kocerlabs.mvvmdatabinding.data.network.model.UserResponse

interface AuthListener {
    /**
     * Data binding ile email ve password girdilerini alacağımız için, ekrana mesaj basarken view katmanında olmamız gerekiyor.
     * Email ve Password değişkenleri bizim view modelimizde, ekrana bir şey bastırmak için view'e ihtiyacımız var.
     * Bu yüzden listener tanımlayıp onu activitym'de ya da fragmentımda implement edeceğim.
     *
     * Daha sonra ona viewModel içinde ihtiyacım var. Bu yüzden viewModel içinde authListener tanımlayacağım.
     */
    fun onStarted()
    fun onSuccess(body: UserResponse?)
    fun onFailure(message: String)
}