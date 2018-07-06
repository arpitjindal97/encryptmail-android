package com.encryptmail.email.ui.login

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.encryptmail.email.R
import com.encryptmail.email.ui.base.BaseActivity
import com.google.android.gms.common.SignInButton
import kotlinx.android.synthetic.main.login_activity.*

class LoginActivity : BaseActivity() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.login_activity)
        supportActionBar?.title = "Login"
        sign_in_button.setSize(SignInButton.SIZE_WIDE)

        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }

}