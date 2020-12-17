package com.apolis.homero.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.apolis.homero.R
import com.apolis.homero.app.MyApplication
import com.apolis.homero.helpers.SessionManager
import com.apolis.homero.helpers.openActivity
import com.apolis.homero.ui.auth.LoginActivity

class SplashActivity : AppCompatActivity() {

    private val SPLASH_DISPLAY_LENGTH: Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        init()
    }

    private fun init() {
        //Handler for communicating back with the UI thread
        Handler().postDelayed(
            Runnable {
                // this method to be called after timer is over
                chooseStartScreen()
                finish()
            }, SPLASH_DISPLAY_LENGTH
        )
    }

    private fun chooseStartScreen() {
        var sessionManager = SessionManager.getInstance()
        if(sessionManager.isLoggedIn) {
            openActivity(this, HomeActivity::class.java, null)
        } else {
            openActivity(this, LoginActivity::class.java, null)
        }
    }
}