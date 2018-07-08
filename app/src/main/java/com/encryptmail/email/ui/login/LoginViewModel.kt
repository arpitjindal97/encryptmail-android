package com.encryptmail.email.ui.login

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Intent
import com.androidhuman.rxfirebase2.auth.rxSignInWithCredential
import com.encryptmail.email.MyApplication
import com.encryptmail.email.data.Repository
import com.encryptmail.email.ui.base.BaseViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInApi
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import javax.inject.Inject

class LoginViewModel : BaseViewModel() {

    val RC_SIGN_IN = 9001

    init {
        MyApplication.appComponent.inject(this)
    }

    @Inject
    lateinit var repository: Repository

    var loginSuccess = MutableLiveData<Boolean>()
    fun getLoginSuccess(): LiveData<Boolean> {
        return loginSuccess
    }

    fun onActivityResult(requestCode: Int, data: Intent?) {

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account)

            } catch (e: ApiException) {
                loginSuccess.value = false
            }
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {


        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)

        FirebaseAuth.getInstance().rxSignInWithCredential(credential).subscribe(
                {
                    repository.insertAccount(acct)
                    loginSuccess.value = true
                }) {
            loginSuccess.value = false
        }

    }
}