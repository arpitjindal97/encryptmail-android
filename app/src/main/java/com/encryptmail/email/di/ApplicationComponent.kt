package com.encryptmail.email.di

import com.encryptmail.email.MyApplication
import com.encryptmail.email.data.Repository
import com.encryptmail.email.ui.login.LoginActivity
import com.encryptmail.email.ui.login.LoginViewModel
import com.encryptmail.email.ui.main.MainActivity
import com.encryptmail.email.ui.main.MainViewModel
import com.encryptmail.email.ui.splash.SplashActivity
import com.encryptmail.email.ui.splash.SplashViewModel
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

    fun inject(splashActivity: SplashActivity)

    fun inject(splashViewModel: SplashViewModel)

    fun inject(loginViewModel: LoginViewModel)
    fun inject(loginActivity: LoginActivity)
    fun inject(mainActivity: MainActivity)
    fun inject(mainViewModel: MainViewModel)
    fun inject(repository: Repository)




}