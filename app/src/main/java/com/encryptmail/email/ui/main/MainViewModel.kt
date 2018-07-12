package com.encryptmail.email.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import com.encryptmail.email.MyApplication
import com.encryptmail.email.data.Repository
import com.encryptmail.email.data.db.Account
import com.encryptmail.email.ui.base.BaseViewModel
import com.encryptmail.email.utils.AccountUtils
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import net.openid.appauth.AuthState
import javax.inject.Inject

class MainViewModel : BaseViewModel() {
    @Inject
    lateinit var repository: Repository

    init {
        MyApplication.appComponent.inject(this)
    }

    private var activeProfile = MutableLiveData<Account>()

    fun getListAccount(): LiveData<Array<Account>> {
        return repository.getAllAccount()
    }

    fun getActiveProfile(): LiveData<Account> {
        return activeProfile
    }

    fun setActiveProfile(authState: Account) {

        activeProfile.value = authState
    }
}