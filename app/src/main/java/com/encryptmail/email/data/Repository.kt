package com.encryptmail.email.data

import android.arch.lifecycle.*
import com.encryptmail.email.data.db.Account
import com.encryptmail.email.data.db.AccountDao
import com.encryptmail.email.data.db.ActiveAccount
import com.encryptmail.email.data.db.ActiveAccountDao
import com.encryptmail.email.data.db.model.UserInfo
import com.encryptmail.email.data.network.api.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import net.openid.appauth.AuthState
import java.util.concurrent.Executor
import javax.inject.Singleton

@Singleton
class Repository constructor(
        private var accountDao: AccountDao,
        private var activeAccountDao: ActiveAccountDao,
        private var executor: Executor,
        private var apiService: ApiService
) {

    fun getAccount(email: String): LiveData<Account> {
        return accountDao.getAccount(email)
    }

    fun getAllAccount(): LiveData<Array<Account>> {
        return accountDao.getAllAccount()
    }

    fun insertAccount(authState: AuthState) {

        val endPoint = authState.authorizationServiceConfiguration?.discoveryDoc?.userinfoEndpoint.toString()

        val bearer = "Bearer ${authState.accessToken}"

        val userInfo = apiService.getUserInfo(endPoint, bearer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

        userInfo.subscribe { response ->
            val account = Account(
                    response.email,
                    response as UserInfo,
                    authState.jsonSerializeString() as String
            )
            executor.execute {
                accountDao.insertAccount(account)
                updateActiveAccount(account)
            }
        }

    }

    fun getActiveAccount(): LiveData<ActiveAccount> {
        return activeAccountDao.getActiveAccount()
    }

    fun updateActiveAccount(account: Account) {
        val activeAccount = ActiveAccount(account.email, account.userInfo)
        activeAccountDao.updateAccount(activeAccount)
    }

}