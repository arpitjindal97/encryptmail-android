package com.encryptmail.email.ui.login

import com.encryptmail.email.MyApplication
import com.encryptmail.email.data.Repository
import com.encryptmail.email.ui.base.BaseViewModel
import net.openid.appauth.AuthState
import javax.inject.Inject

class LoginViewModel : BaseViewModel() {

    @Inject
    lateinit var repository: Repository


    init {
        MyApplication.appComponent.inject(this)
    }

    fun insertIntoDatabase(authState: AuthState) {
        repository.insertAccount(authState)
    }
}
