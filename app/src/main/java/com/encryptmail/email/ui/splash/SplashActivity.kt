package com.encryptmail.email.ui.splash

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import com.encryptmail.email.R
import com.encryptmail.email.data.db.ActiveAccount
import com.encryptmail.email.ui.base.BaseActivity
import com.encryptmail.email.ui.login.LoginActivity
import com.encryptmail.email.ui.main.MainActivity

class SplashActivity : BaseActivity() {

    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

    }

    override fun onStart() {
        super.onStart()

        viewModel = ViewModelProviders.of(this).get(SplashViewModel::class.java)
        viewModel.getActiveAccount().observe(this, Observer { activeAccount ->

            updateUI(activeAccount)

        })
    }

    private fun updateUI(activeAccount: ActiveAccount?) {

        val intent: Intent
        if (activeAccount != null) {
            /*
            if (!GoogleSignIn.hasPermissions(
                            GoogleSignIn.getLastSignedInAccount(this),
                            Scope("https://mail.google.com/"))) {
                Toast.makeText(this,"opening screen",Toast.LENGTH_LONG).show()
                GoogleSignIn.requestPermissions(
                        this,
                        RC_REQUEST_PERMISSION_SUCCESS_CONTINUE_FILE_CREATION,
                        GoogleSignIn.getLastSignedInAccount(this),
                        Scope("https://mail.google.com/"))
            } else {
                Toast.makeText(this, "It exists", Toast.LENGTH_LONG).show()
            }
            */
            intent = Intent(this, MainActivity::class.java)

        } else {
            intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("back", false)
        }
        startActivity(intent)
        finish()

    }

}
