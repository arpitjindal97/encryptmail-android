package com.encryptmail.email.utils

import android.util.Log
import com.encryptmail.email.data.db.Account
import com.google.android.gms.auth.api.signin.*
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import net.openid.appauth.AuthState

class AccountUtils {
    companion object {
        fun getArrayAuthState(arrayAccount: Array<Account>?): ArrayList<AuthState> {
            val arrayAuthStat = ArrayList<AuthState>()

            if (arrayAccount != null) {
                for (account in arrayAccount) {

                    val authStat = AuthState.jsonDeserialize(account.authStateJSON)

                    arrayAuthStat.add(authStat)
                }
            }
            return arrayAuthStat
        }

        fun getProfileDrawerItem(googleAccount: GoogleSignInAccount): ProfileDrawerItem {
            return ProfileDrawerItem()
                    .withName(googleAccount?.displayName)
                    .withEmail(googleAccount?.email)
                    .withIcon(googleAccount?.photoUrl)
        }
    }
}