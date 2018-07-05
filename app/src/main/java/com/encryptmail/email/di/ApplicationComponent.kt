package com.encryptmail.email.di

import com.encryptmail.email.MyApplication
import com.encryptmail.email.ui.base.BaseActivity
import com.encryptmail.email.ui.base.BaseViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(myApplication: MyApplication)

    fun inject(baseActivity: BaseActivity)

    fun inject(baseViewModel: BaseViewModel)


}