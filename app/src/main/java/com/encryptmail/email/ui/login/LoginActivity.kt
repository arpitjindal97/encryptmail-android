package com.encryptmail.email.ui.login

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.encryptmail.email.MyApplication
import com.encryptmail.email.R
import com.encryptmail.email.ui.base.BaseActivity
import com.encryptmail.email.ui.main.MainActivity
import com.encryptmail.email.utils.ConstantsUtil
import com.google.android.gms.common.SignInButton
import kotlinx.android.synthetic.main.login_activity.*
import net.openid.appauth.AuthState

class LoginActivity : BaseActivity(), View.OnClickListener {

    private lateinit var viewModel: LoginViewModel
    private var back: Boolean = false

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
        viewModel.getAuthStateLiveData().observe(this, Observer { authState ->
            viewModel.insertIntoDatabase(authState as AuthState)
            updateUI()
        })

    }

    private fun updateUI() {
        if (back) {
            finish()
        } else {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        viewModel.onActivityResult(requestCode, data, this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.sign_in_button -> {
                viewModel.signIn(this, ConstantsUtil.RC_OPENID)
            }
        }
    }
}