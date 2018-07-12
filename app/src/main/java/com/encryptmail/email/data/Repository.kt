package com.encryptmail.email.data

import android.arch.lifecycle.*
import android.content.Context
import android.content.Intent
import android.util.Log
import com.encryptmail.email.data.db.Account
import com.encryptmail.email.data.db.AccountDao
import com.encryptmail.email.data.db.model.UserInfo
import com.encryptmail.email.data.network.LoginRequest
import com.encryptmail.email.data.network.api.ApiService
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import net.openid.appauth.AuthState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor
import javax.inject.Singleton

@Singleton
class Repository constructor(
        private var accountDao: AccountDao,
        private var executor: Executor,
        private var loginRequest: LoginRequest,
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

        val userInfo = apiService.getUserInfo(endPoint,bearer)
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
            }
        }

    }

    fun signIn(context: Context, requestCode: Int) {
        loginRequest.signIn(context, requestCode)
    }

    fun processActivityResult(requestCode: Int,
                              data: Intent?,
                              authState: MutableLiveData<AuthState>,
                              context: Context) {
        loginRequest.processActivityResult(requestCode, data, authState, context)
    }
}