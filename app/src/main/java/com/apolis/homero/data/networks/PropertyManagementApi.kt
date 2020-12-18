package com.apolis.homero.data.networks

import com.apolis.homero.app.Config
import com.apolis.homero.app.Endpoints
import com.apolis.homero.data.models.LoginResponse
import com.apolis.homero.data.models.RegisterResponse
import com.apolis.homero.data.models.User
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.POST

interface PropertyManagementApi {

    @POST("${Endpoints.URL_LOGIN}")
    fun loginUser(@Body user: User): Call<LoginResponse>

    @POST("${Endpoints.URL_REGISTER}")
    fun registerUser(@Body user: User): Call<RegisterResponse>

    companion object {
        operator fun invoke(): PropertyManagementApi {
            return Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PropertyManagementApi::class.java)
        }
    }
}