package com.apolis.homero.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.apolis.homero.repositories.AuthRepository

class AuthViewModel : ViewModel() {
    var name: String? = null
    var email: String? = null
    var password: String? = null
    var type: String? = null

    var authListener: AuthListener? = null

    fun onLoginButtonClicked(view: View) {
        if(email.isNullOrEmpty()||password.isNullOrEmpty()) {
            authListener?.onFailure("Registration Failed")
            return
        }
        var loginResponse = AuthRepository().loginUser(email!!, password!!)
        authListener?.onSuccess(loginResponse)
    }

    fun onRegisterButtonClicked(view: View) {
        if(name.isNullOrEmpty()||email.isNullOrEmpty()||password.isNullOrEmpty()||type.isNullOrEmpty()) {
            authListener?.onFailure("Login Failed")
            return
        }
        var registerResponse = AuthRepository().registerUser(name!!, email!!, password!!, type!!)
        authListener?.onSuccess(registerResponse)
    }

    fun onRedirect(view: View) {
        authListener?.onRedirect()
    }
}