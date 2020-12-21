package com.apolis.homero.data.networks

import com.apolis.homero.app.Config
import com.apolis.homero.app.Endpoints
import com.apolis.homero.data.models.*
import okhttp3.MultipartBody
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.*

interface PropertyManagementApi {

    @POST(Endpoints.URL_LOGIN)
    fun loginUser(@Body user: User): Call<LoginResponse>

    @POST(Endpoints.URL_REGISTER)
    fun registerUser(@Body user: User): Call<RegisterResponse>

    @GET(Endpoints.URL_PROPERTY)
    fun getProperty(): Call<PostPropertyResponse>

    @POST(Endpoints.URL_PROPERTY)
    fun addProperty(@Body property: PropertyData): Call<PostPropertyResponse>

    @Multipart
    @POST(Endpoints.URL_IMAGE_UPLOAD)
    fun postPropertyImage(@Part image: MultipartBody.Part): Call<ImageResponse>

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