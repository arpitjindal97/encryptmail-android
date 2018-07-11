package com.encryptmail.email.utils

import android.util.Log
import com.encryptmail.email.data.db.Account
import com.google.android.gms.auth.api.signin.*
import com.mikepenz.materialdrawer.model.ProfileDrawerItem

class AccountUtils {
    companion object {
        fun getArrayAuthStat(arrayAccount: Array<Account>?): ArrayList<String> {
            val arrayAuthStat = ArrayList<String>()

            if (arrayAccount != null) {
                for (account in arrayAccount) {

                    val authStat = account.authStatJSON

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