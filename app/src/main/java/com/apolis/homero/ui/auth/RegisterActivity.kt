package com.apolis.homero.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.apolis.homero.R
import com.apolis.homero.databinding.ActivityRegisterBinding
import com.apolis.homero.helpers.openActivity
import com.apolis.homero.helpers.toast

class RegisterActivity : AppCompatActivity(), AuthListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        init()
    }

    private fun init() {
        var registerBinding: ActivityRegisterBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_register, null, false)
        var authViewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        registerBinding.userInfo = authViewModel
        authViewModel.authListener = this
        setContentView(registerBinding.root)
    }

    override fun onSuccess(authResponse: LiveData<String>) {
        authResponse.observe(this, Observer{
            toast(it)
        })
    }

    override fun onFailure(message: String) {
        toast("Failed")
    }

    override fun onRedirect() {
        openActivity(this, LoginActivity::class.java, null)
    }
}