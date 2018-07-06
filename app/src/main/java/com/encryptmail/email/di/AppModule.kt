package com.encryptmail.email.di

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: Application) {

    @Provides
    @Singleton
    fun provideContext(): Context = app

    @Provides
    fun provideFirebaseAuth(): LiveData<FirebaseAuth> {

        val auth = MutableLiveData<FirebaseAuth>()
        auth.value = FirebaseAuth.getInstance()
        return auth
    }
}