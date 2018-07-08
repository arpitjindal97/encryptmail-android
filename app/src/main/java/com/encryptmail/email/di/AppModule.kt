package com.encryptmail.email.di

import android.app.Application
import android.content.Context
import android.util.Log
import com.encryptmail.email.MyApplication
import com.encryptmail.email.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.Scope
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
                .requestScopes(Scope("https://mail.google.com/"))
                .requestEmail()
                .build()

        Log.i("ARPIT","creating GoogleSignInClient instance")
        return GoogleSignIn.getClient(context, gso)
    }
}