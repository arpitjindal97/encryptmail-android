package com.encryptmail.email

import android.app.Application
import com.encryptmail.email.di.AppComponent
import com.encryptmail.email.di.AppModule
import com.encryptmail.email.di.DaggerAppComponent


class MyApplication : Application() {

    companion object {

        lateinit var appComponent: AppComponent

    }
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        appComponent.inject(this)

    }
}