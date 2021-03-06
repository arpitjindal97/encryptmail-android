package com.encryptmail.email.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.encryptmail.email.MyApplication
import com.encryptmail.email.data.Repository
import com.encryptmail.email.data.db.Account
import com.encryptmail.email.data.db.ActiveAccount
import com.encryptmail.email.ui.base.BaseViewModel
import javax.inject.Inject

class MainViewModel : BaseViewModel() {
    @Inject
    lateinit var repository: Repository

    init {
        MyApplication.appComponent.inject(this)
    }


    fun getListAccount(): LiveData<Array<Account>> {
        return repository.getAllAccount()
    }

    fun getActiveAccount(): LiveData<ActiveAccount> {
        return repository.getActiveAccount()
    }

}