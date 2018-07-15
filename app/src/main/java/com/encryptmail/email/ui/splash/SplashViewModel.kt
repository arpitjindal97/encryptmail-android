package com.encryptmail.email.ui.splash

import android.arch.lifecycle.LiveData
import com.encryptmail.email.MyApplication
import com.encryptmail.email.data.Repository
import com.encryptmail.email.data.db.ActiveAccount
import com.encryptmail.email.ui.base.BaseViewModel
import javax.inject.Inject

class SplashViewModel : BaseViewModel() {

    @Inject
    lateinit var repository: Repository

    init {
        MyApplication.appComponent.inject(this)
    }

    fun getActiveAccount(): LiveData<ActiveAccount> {
        return repository.getActiveAccount()
    }
}