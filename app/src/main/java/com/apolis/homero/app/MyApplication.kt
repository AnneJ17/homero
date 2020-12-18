package com.apolis.homero.app

import android.app.Application

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        var instance: MyApplication? = null
        var instanceEndpoints: Endpoints? = null
    }
}
