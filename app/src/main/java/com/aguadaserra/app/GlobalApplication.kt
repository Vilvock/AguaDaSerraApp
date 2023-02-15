package com.aguadaserra.app

import android.app.Application
import android.content.res.Resources

class GlobalApplication(): Application() {

    override fun onCreate() {
        super.onCreate()

//        RetrofitServices.init(this)
    }

    fun getAppResources(): Resources? {
        return resources
    }

}