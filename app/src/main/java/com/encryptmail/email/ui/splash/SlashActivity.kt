package com.encryptmail.email.ui.splash

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.encryptmail.email.R
import com.encryptmail.email.ui.base.BaseActivity
import com.encryptmail.email.ui.login.LoginActivity
import com.encryptmail.email.ui.main.MainActivity
import com.google.firebase.auth.FirebaseAuth

class SlashActivity : BaseActivity() {

    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

        Handler().postDelayed({
            prepareViewModel()
        }, 1000)

    }


    private fun prepareViewModel() {

        viewModel = ViewModelProviders.of(this).get(SplashViewModel::class.java)
        viewModel.getFireAuth().observe(this, Observer { firebaseAuth ->
            updateUI(firebaseAuth)
        })
    }

    private fun updateUI(firebaseAuth: FirebaseAuth?) {

        var intent: Intent = if (firebaseAuth?.currentUser != null) {
            Intent(this, MainActivity::class.java)
        } else {
            Intent(this, LoginActivity::class.java)
        }

        startActivity(intent)
        finish()
    }
}
