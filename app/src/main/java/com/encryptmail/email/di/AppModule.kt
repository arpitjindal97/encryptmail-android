package com.encryptmail.email.di

import android.content.Context
import com.encryptmail.email.MyApplication
import com.encryptmail.email.R
import com.encryptmail.email.data.Repository
import com.encryptmail.email.data.db.AccountDao
import com.encryptmail.email.data.network.LoginRequest
import com.encryptmail.email.data.network.api.ApiService
import com.encryptmail.email.data.network.login.LoginGoogle
import com.encryptmail.email.ui.login.LoginActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.Scope
import com.google.android.gms.drive.Drive
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(app: MyApplication): Context = app

    @Provides
    @Singleton
    fun provideLoginGoogle(): LoginGoogle = LoginGoogle()

    @Provides
    @Singleton
    fun provideLoginRequest(loginGoogle: LoginGoogle): LoginRequest = LoginRequest(loginGoogle)

    @Provides
    @Singleton
    fun provideRepository(accountDao: AccountDao,
                          executor: Executor,
                          loginRequest: LoginRequest,
                          apiService: ApiService): Repository =
            Repository(accountDao, executor, loginRequest, apiService)

    @Provides
    @Singleton
    fun provideApiService(): ApiService =
            Retrofit.Builder()
                    .baseUrl("https://www.googleapis.com")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiService::class.java)
}