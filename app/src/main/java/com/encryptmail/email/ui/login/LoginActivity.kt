package com.encryptmail.email.ui.login

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.encryptmail.email.MyApplication
import com.encryptmail.email.R
import com.encryptmail.email.ui.base.BaseActivity
import com.encryptmail.email.ui.main.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.SignInButton
import kotlinx.android.synthetic.main.login_activity.*
import javax.inject.Inject

class LoginActivity : BaseActivity(), View.OnClickListener {


    private lateinit var viewModel: LoginViewModel
    private var back: Boolean = false

    @Inject
    lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.login_activity)
        MyApplication.appComponent.inject(this)

        back = intent.extras?.getBoolean("back") as Boolean

        if (back) {
            supportActionBar?.title = "Add Email Account"
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        } else {
            supportActionBar?.title = "Login"
        }

        sign_in_button.setSize(SignInButton.SIZE_WIDE)
        sign_in_button.setOnClickListener(this)

        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        viewModel.getLoginSuccess().observe(this, Observer { loginSuccess ->
            onLoginSuccess(loginSuccess)
        })

    }

    private fun onLoginSuccess(loginSuccess: Boolean?) {
        if (loginSuccess == true) {
            if (back) {
                finish()
            } else {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        } else {
            Toast.makeText(this, "Login Failed !", Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        viewModel.onActivityResult(requestCode, data)
    }

    private fun googleSignIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, viewModel.RC_SIGN_IN)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.sign_in_button -> {
                googleSignIn()
            }
        }
    }
}