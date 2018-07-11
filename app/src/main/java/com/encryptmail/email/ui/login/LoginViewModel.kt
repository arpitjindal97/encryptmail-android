package com.encryptmail.email.ui.login

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.content.Intent
import com.encryptmail.email.MyApplication
import com.encryptmail.email.data.Repository
import com.encryptmail.email.ui.base.BaseViewModel
import javax.inject.Inject

class LoginViewModel : BaseViewModel() {

    @Inject
    lateinit var repository: Repository

    private var authStatLiveData = MutableLiveData<String>()

    init {
        MyApplication.appComponent.inject(this)
    }

    fun googleSignIn(context: Context) {
        repository.googleSignIn(context)
    }

    fun getAuthStatLiveData(): LiveData<String> {
        return authStatLiveData
    }

    fun onActivityResult(requestCode: Int, data: Intent?, context: Context) {
        repository.processActivityResult(requestCode, data, authStatLiveData, context)
    }

    fun insertIntoDatabase(authStat: String) {
        repository.insertAccount(authStat)
    }
}
