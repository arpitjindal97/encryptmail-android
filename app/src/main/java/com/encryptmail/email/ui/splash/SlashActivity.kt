package com.encryptmail.email.ui.splash

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.encryptmail.email.R
import com.encryptmail.email.ui.base.BaseActivity
import com.encryptmail.email.ui.login.LoginActivity
import com.encryptmail.email.ui.main.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthCredential

class SlashActivity : BaseActivity() {

    companion object {
        const val RC_REQUEST_PERMISSION_SUCCESS_CONTINUE_FILE_CREATION = 444
    }

    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

        Handler().postDelayed({
            updateUI()
        }, 1000)

    }


    private fun updateUI() {

        var intent: Intent
        if (FirebaseAuth.getInstance().currentUser != null) {
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (RC_REQUEST_PERMISSION_SUCCESS_CONTINUE_FILE_CREATION == requestCode) {
                Toast.makeText(this, "got the permission", Toast.LENGTH_LONG).show()
            }
        }
    }
}
