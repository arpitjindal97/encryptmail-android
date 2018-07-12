package com.encryptmail.email.data.network.login

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import com.androidhuman.rxfirebase2.auth.rxSignInWithCredential
import com.encryptmail.email.R
import com.encryptmail.email.ui.login.LoginActivity
import com.encryptmail.email.utils.ConstantsUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import net.openid.appauth.*
import javax.inject.Singleton

@Singleton
class LoginGoogle {

    private lateinit var service: AuthorizationService

    fun signIn(context: Context) {

        AuthorizationServiceConfiguration.fetchFromIssuer(
                Uri.parse("https://accounts.google.com")
        ) { authServiceConfig, _ ->


            val authRequest = AuthorizationRequest.Builder(
                    authServiceConfig as AuthorizationServiceConfiguration,
                    context.getString(R.string.default_android_client_id),
                    ResponseTypeValues.CODE,
                    Uri.parse("com.encryptmail.email:/oauth2callback"))
                    .setScopes("openid email profile https://www.googleapis.com/auth/drive")
                    .build()


            service = AuthorizationService(context)
            val intent = service.getAuthorizationRequestIntent(authRequest)
            (context as LoginActivity).startActivityForResult(intent, ConstantsUtil.RC_OPENID)
        }
    }

    fun processActivityResult(data: Intent?,
                              authStateLiveData: MutableLiveData<AuthState>,
                              context: Context) {

        service.dispose()
        AuthorizationServiceConfiguration.fetchFromIssuer(
                Uri.parse("https://accounts.google.com")) { serviceConfig,
                                                            ex ->

            if (ex != null) {
                Log.e("ARPIT", "Failed to get configuration")
                return@fetchFromIssuer
            }
            service = AuthorizationService(context)

            val authState = AuthState(serviceConfig as AuthorizationServiceConfiguration)
            val authResp = AuthorizationResponse.fromIntent(data as Intent)
            val authEx = AuthorizationException.fromIntent(data)

            authState.update(authResp, authEx)

            service.performTokenRequest(authResp?.createTokenExchangeRequest() as TokenRequest)
            { tokenResp, tokenEx ->

                authState.update(tokenResp, tokenEx)

                val cred = GoogleAuthProvider.getCredential(authState.lastTokenResponse?.idToken, null)

                FirebaseAuth.getInstance().rxSignInWithCredential(cred).subscribe({
                    service.dispose()
                    authStateLiveData.value = authState
                }) {}

            }
        }

    }
}

