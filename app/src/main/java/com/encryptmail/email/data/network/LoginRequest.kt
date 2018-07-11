package com.encryptmail.email.data.network

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.androidhuman.rxfirebase2.auth.rxSignInWithCredential
import com.encryptmail.email.R
import com.encryptmail.email.ui.login.LoginActivity
import com.encryptmail.email.utils.ConstantsUtil
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Scope
import com.google.android.gms.drive.Drive
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import javax.inject.Singleton

@Singleton
class LoginRequest {

    fun googleSignIn(context: Context) {

        val gso = GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.default_web_client_id))
                .requestServerAuthCode(context.getString(R.string.default_web_client_id))
                .requestScopes(Scope("https://www.googleapis.com/auth/drive"))
                .requestScopes(Drive.SCOPE_FILE)
                .requestScopes(Drive.SCOPE_APPFOLDER)
                .requestEmail()
                .build()
        val googleSignInClient = GoogleSignIn.getClient(context, gso)
        val signInIntent = googleSignInClient.signInIntent
        (context as LoginActivity).startActivityForResult(signInIntent, ConstantsUtil.RC_GOOGLE_SIGN_IN)
    }

    fun processActivityResult(requestCode: Int,
                              data: Intent?,
                              authStatLiveData: MutableLiveData<String>,
                              context: Context) {


        if (requestCode == ConstantsUtil.RC_GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account, authStatLiveData, context)

            } catch (e: ApiException) {
                Toast.makeText(context, "Error : " + e.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(
            account: GoogleSignInAccount,
            authStatLiveData: MutableLiveData<String>,
            context: Context) {

        val credential = GoogleAuthProvider.getCredential(account.idToken, null)

        FirebaseAuth.getInstance().rxSignInWithCredential(credential).subscribe(
                {
                    val authStat = "token received"
                    exchangeAuthCode(authStat,authStatLiveData)

                }) { e ->

            Toast.makeText(context, "Error : " + e.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun exchangeAuthCode(authStat: String,authStatLiveData: MutableLiveData<String>) {

        // exchange and set authStat.value.accessToken
        authStatLiveData.value = authStat

    }
}