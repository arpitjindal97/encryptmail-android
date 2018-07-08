package com.encryptmail.email.data

import android.arch.lifecycle.*
import com.encryptmail.email.data.db.Account
import com.encryptmail.email.data.db.AccountDao
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import java.util.concurrent.Executor
import javax.inject.Singleton

@Singleton
class Repository constructor(
        private var accountDao: AccountDao,
        private var executor: Executor
)  {

    fun getAccount(email: String): LiveData<Account> {
        return accountDao.getAccount(email)
    }

    fun getAllAccount(): LiveData<Array<Account>> {
        return accountDao.getAllAccount()
    }

    fun insertAccount(googleSignInAccount: GoogleSignInAccount) {

        val account = Account(
                googleSignInAccount.email.toString(),
                googleSignInAccount.toJsonForStorage()
        )
        executor.execute {
            accountDao.insertAccount(account)
        }
    }

}