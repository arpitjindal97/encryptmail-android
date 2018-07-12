package com.encryptmail.email.ui.login

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.content.Intent
import com.encryptmail.email.MyApplication
import com.encryptmail.email.data.Repository
import com.encryptmail.email.ui.base.BaseViewModel
import net.openid.appauth.AuthState
import javax.inject.Inject

class LoginViewModel : BaseViewModel() {

    @Inject
    lateinit var repository: Repository

    private var authStateLiveData = MutableLiveData<AuthState>()

    init {
        MyApplication.appComponent.inject(this)
    }

    fun signIn(context: Context,requestCode: Int) {
        repository.signIn(context,requestCode)
    }

    fun getAuthStateLiveData(): LiveData<AuthState> {
        return authStateLiveData
    }

    fun onActivityResult(requestCode: Int, data: Intent?, context: Context) {
        repository.processActivityResult(requestCode, data, authStateLiveData, context)
    }

    fun insertIntoDatabase(authState: AuthState) {
        repository.insertAccount(authState)
    }
}
