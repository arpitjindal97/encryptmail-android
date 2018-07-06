package com.encryptmail.email.ui.login

import com.encryptmail.email.MyApplication
import com.encryptmail.email.ui.base.BaseViewModel

class LoginViewModel : BaseViewModel() {
    init {
        MyApplication.appComponent.inject(this)
    }
}