package com.encryptmail.email.utils


class ConstantsUtil {
    companion object {
        const val RC_GOOGLE_SIGN_IN = 9001
        const val RC_OPENID = 9002


        const val GOOGLE_SCOPE = "openid email profile https://mail.google.com/"
        const val GOOGLE_URL = "https://mail.google.com/"
        const val GOOGLE_SERVICE_URL = "https://accounts.google.com"

        const val LOGIN_CALLBACK_URL = "com.encryptmail.email:/oauth2callback"

    }
}