package com.encryptmail.email.ui.splash

import com.encryptmail.email.MyApplication
import com.encryptmail.email.ui.base.BaseViewModel

class SplashViewModel : BaseViewModel() {

    init {
        MyApplication.appComponent.inject(this)
    }


}