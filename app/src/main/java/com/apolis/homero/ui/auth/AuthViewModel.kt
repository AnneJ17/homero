package com.apolis.homero.ui.auth

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.apolis.homero.data.models.User
import com.apolis.homero.data.repositories.AuthRepository

class AuthViewModel : ViewModel() {
    var name: String? = null
    var email: String? = null
    var password: String? = null
    var type: String? = null

    var authListener: AuthListener? = null

    fun onLoginButtonClicked(view: View) {
        Log.d("ABC", "Inside AuthViewModel: onLoginButtonClicked")
        if(email.isNullOrEmpty()||password.isNullOrEmpty()) {
            authListener?.onFailure("Registration Failed")
            return
        }
        var loginInfo = User(email = email, password = password)
        var loginResponse = AuthRepository().loginUser(loginInfo)
        authListener?.onSuccess(loginResponse)
    }

    fun onRegisterButtonClicked(view: View) {
        Log.d("ABC", "Inside AuthViewModel: onRegisterButtonClicked")
        if(name.isNullOrEmpty()||email.isNullOrEmpty()||password.isNullOrEmpty()||type.isNullOrEmpty()) {
            authListener?.onFailure("Please complete all the fields")
            return
        }
        var registerInfo = User(name = name, email = email, password = password, type = type)
        var registerResponse = AuthRepository().registerUser(registerInfo)
        authListener?.onSuccess(registerResponse)
    }

    fun onRadioLandlordClicked(view: View){
        Log.d("ABC", "Landlord clicked")
        type = "landlord"
    }
    fun onRadioTenantClicked(view: View){
        Log.d("ABC", "Tenant clicked")
        type = "tenant"
    }

    fun onRedirect(view: View) {
        authListener?.onRedirect()
    }
}