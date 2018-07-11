package com.encryptmail.email.data

import android.arch.lifecycle.*
import android.content.Context
import android.content.Intent
import android.os.Handler
import com.encryptmail.email.data.db.Account
import com.encryptmail.email.data.db.AccountDao
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.encryptmail.email.data.network.LoginRequest
import com.encryptmail.email.utils.AccountUtils
import java.util.concurrent.Executor
import javax.inject.Singleton

@Singleton
class Repository constructor(
        private var accountDao: AccountDao,
        private var executor: Executor,
        private var loginRequest: LoginRequest
) {

    fun getAccount(email: String): LiveData<Account> {
        return accountDao.getAccount(email)
    }

    fun getAllAccount(): LiveData<Array<Account>> {
         return  accountDao.getAllAccount()
    }

    fun insertAccount(authStat: String) {

        val account = Account(
                "arpitjindal97@gmail.com",
                authStat
        )
        executor.execute {
            accountDao.insertAccount(account)
        }
    }

    fun googleSignIn(context: Context) {
        loginRequest.googleSignIn(context)
    }

    fun processActivityResult(requestCode: Int,
                              data: Intent?,
                              authStat: MutableLiveData<String>,
                              context: Context) {
        loginRequest.processActivityResult(requestCode, data, authStat, context)
    }
}