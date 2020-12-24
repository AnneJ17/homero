package com.apolis.homero.ui.auth

import androidx.lifecycle.LiveData
import com.apolis.homero.data.models.User

interface AuthListener {
    fun onSuccess(authResponse: LiveData<User>)
    fun onFailure(message: String)
    fun onRedirect()
}