package com.apolis.homero.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.apolis.homero.R
import com.apolis.homero.databinding.ActivityLoginBinding
import com.apolis.homero.helpers.openActivity
import com.apolis.homero.helpers.toast

class LoginActivity : AppCompatActivity(), AuthListener {

    lateinit var loginBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
    }

    private fun init() {
        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        var authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        loginBinding.user = authViewModel
        authViewModel.authListener = this
    }

    override fun onSuccess(authResponse: LiveData<String>) {
        authResponse.observe(this, Observer {
            toast(it)
        })
    }

    override fun onFailure(message: String) {
        toast("Failed")
    }

    override fun onRedirect() {
        openActivity(this, RegisterActivity::class.java, null)
    }
}