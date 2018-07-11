package com.encryptmail.email.di

import android.content.Context
import com.encryptmail.email.MyApplication
import com.encryptmail.email.R
import com.encryptmail.email.data.Repository
import com.encryptmail.email.ui.base.BaseActivity
import com.encryptmail.email.ui.login.LoginActivity
import com.encryptmail.email.ui.login.LoginViewModel
import com.encryptmail.email.ui.main.MainActivity
import com.encryptmail.email.ui.main.MainViewModel
import com.encryptmail.email.ui.splash.SplashViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.Scope
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RoomModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: MyApplication): Builder

        fun build(): AppComponent
    }

    fun inject(myApplication: MyApplication)

    fun inject(baseActivity: BaseActivity)

    fun inject(splashViewModel: SplashViewModel)

    fun inject(loginViewModel: LoginViewModel)
    fun inject(loginActivity: LoginActivity)
    fun inject(mainActivity: MainActivity)
    fun inject(mainViewModel: MainViewModel)
    fun inject(repository: Repository)




}