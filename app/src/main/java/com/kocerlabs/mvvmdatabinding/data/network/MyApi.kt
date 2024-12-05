package com.kocerlabs.mvvmdatabinding.data.network

import com.kocerlabs.mvvmdatabinding.data.network.model.NetworkConnectionInterceptor
import com.kocerlabs.mvvmdatabinding.data.network.model.UserRequest
import com.kocerlabs.mvvmdatabinding.data.network.model.UserResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface MyApi {
    @POST("user/login")
    suspend fun userLogin(@Body userRequest: UserRequest): Response<UserResponse>


    companion object {
        const val BASE_URL = "https://dummyjson.com/"


        // MyApi() ile çağırmak, bu fonksiyonu çağıracaktır.
        operator fun invoke(networkConnectionInterceptor: NetworkConnectionInterceptor): MyApi {
            val okkHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okkHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }

}
/**
 * Önceki projede ayrı bir retrofit oluşturucu tanımlamıştık ve generic bir fonksiyondu.
 * İstediğimiz class'a göre generic bir retrofit oluşturuyordu.
 * Şimdi ise bu interface'ye özel bir retrofit tanımlamış olduk.
 */