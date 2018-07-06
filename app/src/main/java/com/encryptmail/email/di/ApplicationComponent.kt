package com.encryptmail.email.di

import com.encryptmail.email.MyApplication
import com.encryptmail.email.ui.base.BaseActivity
import com.encryptmail.email.ui.login.LoginViewModel
import com.encryptmail.email.ui.splash.SplashViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(myApplication: MyApplication)

    fun inject(baseActivity: BaseActivity)

    fun inject(splashViewModel: SplashViewModel)

    fun inject(loginViewModel: LoginViewModel)


}