package com.encryptmail.email.di

import android.content.Context
import com.encryptmail.email.MyApplication
import com.encryptmail.email.R
import com.encryptmail.email.data.network.LoginRequest
import com.encryptmail.email.ui.login.LoginActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.Scope
import com.google.android.gms.drive.Drive
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(app: MyApplication): Context = app

    @Provides
    @Singleton
    fun provideGoogleSignInClient(context: Context): GoogleSignInClient {

        val gso = GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.default_web_client_id))
                .requestScopes(Scope("https://www.googleapis.com/auth/drive"))
                .requestScopes(Drive.SCOPE_FILE)
                .requestScopes(Drive.SCOPE_APPFOLDER)
                .requestEmail()
                .build()

        return GoogleSignIn.getClient(context, gso)
    }

    @Provides
    @Singleton
    fun provideLoginRequest(): LoginRequest = LoginRequest()
}