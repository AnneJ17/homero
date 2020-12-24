package com.apolis.homero.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.apolis.homero.R
import com.apolis.homero.data.models.User
import com.apolis.homero.databinding.ActivityRegisterBinding
import com.apolis.homero.helpers.openActivity
import com.apolis.homero.helpers.toast

class RegisterActivity : AppCompatActivity(), AuthListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
    }

    private fun init() {
//        var registerBinding: ActivityRegisterBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_register, null, false)
        var registerBinding: ActivityRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        // Register view model
        var authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        registerBinding.userInfo = authViewModel
        authViewModel.authListener = this
    }

    override fun onSuccess(authResponse: LiveData<User>) {
        authResponse.observe(this, Observer{
            toast("Registration Successful")
            openActivity(this, LoginActivity::class.java, null)
        })
    }

    override fun onFailure(message: String) {
        toast("Failed")
    }

    override fun onRedirect() {
        openActivity(this, LoginActivity::class.java, null)
    }
}