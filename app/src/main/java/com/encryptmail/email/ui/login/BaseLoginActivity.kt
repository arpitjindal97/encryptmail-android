package com.encryptmail.email.ui.login

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import com.androidhuman.rxfirebase2.auth.rxSignInWithCredential
import com.encryptmail.email.R
import com.encryptmail.email.ui.base.BaseActivity
import com.encryptmail.email.utils.ConstantsUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import net.openid.appauth.*

abstract class BaseLoginAcivity : BaseActivity() {

    private lateinit var service: AuthorizationService
    private lateinit var serviceConfiguration: AuthorizationServiceConfiguration
    private lateinit var authState: AuthState
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }


     fun googleSignIn() {

        AuthorizationServiceConfiguration.fetchFromIssuer(
                Uri.parse(ConstantsUtil.GOOGLE_SERVICE_URL)
        ) { authServiceConfig, _ ->

            serviceConfiguration = authServiceConfig as AuthorizationServiceConfiguration
            authState = AuthState(serviceConfiguration)

            val authRequest = AuthorizationRequest.Builder(
                    serviceConfiguration,
                    this.getString(R.string.default_android_client_id),
                    ResponseTypeValues.CODE,
                    Uri.parse(ConstantsUtil.LOGIN_CALLBACK_URL))
                    .setScopes(ConstantsUtil.GOOGLE_SCOPE)
                    .build()

            service = AuthorizationService(this)

            val intent = service.getAuthorizationRequestIntent(authRequest)
            startActivityForResult(intent, ConstantsUtil.RC_OPENID)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val authResp = AuthorizationResponse.fromIntent(data as Intent)
        val authEx = AuthorizationException.fromIntent(data)

        authState.update(authResp, authEx)

        service.performTokenRequest(authResp?.createTokenExchangeRequest() as TokenRequest)
        { tokenResp, tokenEx ->

            if (tokenEx != null) {
                Toast.makeText(this, "Error Logging In", Toast.LENGTH_SHORT).show()
            }

            authState.update(tokenResp, tokenEx)

            //val cred = GoogleAuthProvider.getCredential(authState.lastTokenResponse?.idToken, null)

            //FirebaseAuth.getInstance().rxSignInWithCredential(cred).subscribe({
            service.dispose()
            viewModel.insertIntoDatabase(authState)
            finishUI()
            //}) { finish() }

        }
    }

    abstract fun finishUI()
}
