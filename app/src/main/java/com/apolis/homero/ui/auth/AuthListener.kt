package com.apolis.homero.ui.auth

import androidx.lifecycle.LiveData

interface AuthListener {
    fun onSuccess(authResponse: LiveData<String>)
    fun onFailure(message: String)
    fun onRedirect()
}