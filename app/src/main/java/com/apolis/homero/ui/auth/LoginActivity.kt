package com.apolis.homero.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.apolis.homero.R
import com.apolis.homero.data.models.User
import com.apolis.homero.databinding.ActivityLoginBinding
import com.apolis.homero.helpers.SessionManager
import com.apolis.homero.helpers.d
import com.apolis.homero.helpers.openActivity
import com.apolis.homero.helpers.toast
import com.apolis.homero.ui.MainActivity

class LoginActivity : AppCompatActivity(), AuthListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
    }

    private fun init() {
//        var loginBinding: ActivityLoginBinding =  DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_login, null, false)
        var loginBinding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        // Register view model
        var authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        loginBinding.user = authViewModel
        authViewModel.authListener = this
        d("Login viewmodel part set")
    }

    override fun onSuccess(authResponse: LiveData<User>) {
        authResponse.observe(this, Observer {
            toast("Login Successful")
            SessionManager.getInstance().saveUserInfo(authResponse.value)
            d(it.toString())
            openActivity(this, MainActivity::class.java, null)
            finish()
        })
    }

    override fun onFailure(message: String) {
        toast("Failed")
    }

    override fun onRedirect() {
        openActivity(this, RegisterActivity::class.java, null)
        finish()
    }
}