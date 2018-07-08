package com.encryptmail.email.utils

import android.util.Log
import com.encryptmail.email.data.db.Account
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.gson.Gson
import com.mikepenz.materialdrawer.model.ProfileDrawerItem

class AccountUtils {
    companion object {
        fun getArrayProfileDrawerItem(arrayAccount: Array<Account>): ArrayList<ProfileDrawerItem> {
            val arrayProfile = ArrayList<ProfileDrawerItem>()

            for (account in arrayAccount) {

                val googleAccount =
                        GoogleSignInAccount.fromJsonString(account.googleAccount)

                Log.i("ARPIT", "Account: $googleAccount")
                val profile = ProfileDrawerItem()
                        .withName(googleAccount?.displayName)
                        .withEmail(googleAccount?.email)
                        .withIcon(googleAccount?.photoUrl)

                arrayProfile.add(profile)
            }
            return arrayProfile
        }
    }
}