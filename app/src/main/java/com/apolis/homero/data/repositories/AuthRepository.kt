package com.apolis.homero.data.repositories

import android.util.Log
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
    fun loginUser(user: User): LiveData<User> {
        Log.d("ABC", "Inside AuthRepository: registerUser")
        var user = User(email = user.email, password = user.password)
        val loginResponse = MutableLiveData<User>()
        PropertyManagementApi().loginUser(user)
            .enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.isSuccessful) {
                        loginResponse.value = response.body()?.user
                        Log.d("ABC", loginResponse.value.toString())
                    }
                }
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.d("ABC", t.message.toString())
                }
            })
        return loginResponse
    }

    fun registerUser(user: User): LiveData<User> {
        Log.d("ABC", "Inside AuthRepository: registerUser")
        var user = User(name = user.name, email = user.email, password = user.password, type = user.type)
        val registerResponse = MutableLiveData<User>()
        PropertyManagementApi().registerUser(user)
            .enqueue(object : Callback<RegisterResponse> {
                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>
                ) {
                    if(response.isSuccessful) {
                        registerResponse.value = response.body()?.data
                        Log.d("ABC", registerResponse.value.toString())
                    }
                }
                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    Log.d("ABC", t.message.toString() + " Inside registerUser()")
                }

            })
        return registerResponse
    }
}