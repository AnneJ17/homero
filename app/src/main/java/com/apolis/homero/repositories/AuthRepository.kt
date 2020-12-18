package com.apolis.homero.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.apolis.homero.data.models.LoginResponse
import com.apolis.homero.data.models.RegisterResponse
import com.apolis.homero.data.models.User
import com.apolis.homero.data.networks.PropertyManagementApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepository {
    fun loginUser(email: String, password: String): LiveData<String> {
        var user = User(email = email, password = password)
        val loginResponse = MutableLiveData<String>()
        PropertyManagementApi().loginUser(user)
            .enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.isSuccessful) {
                        loginResponse.value = response.message()
                    }
                }
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    loginResponse.value = t.message
                }
            })
        return loginResponse
    }

    fun registerUser(name: String, email: String, password: String, type: String): LiveData<String> {
        var user = User(name = name, email = email, password = password, type = type)
        val registerResponse = MutableLiveData<String>()
        PropertyManagementApi().registerUser(user)
            .enqueue(object : Callback<RegisterResponse> {
                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>
                ) {
                    if(response.isSuccessful) {
                        registerResponse.value = response.message()
                    }
                }
                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    registerResponse.value = t.message
                }

            })
        return registerResponse
    }
}