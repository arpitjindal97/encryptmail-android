package com.encryptmail.email.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.encryptmail.email.MyApplication
import com.encryptmail.email.R
import com.encryptmail.email.data.db.Account
import com.encryptmail.email.ui.base.BaseActivity
import com.encryptmail.email.utils.AccountUtils
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.drive.Drive
import com.google.android.gms.drive.DriveFolder
import com.google.android.gms.drive.MetadataBuffer
import com.google.android.gms.drive.MetadataChangeSet
import com.google.android.gms.drive.query.Filters
import com.google.android.gms.drive.query.Query
import com.google.android.gms.drive.query.SearchableField
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.mikepenz.materialdrawer.AccountHeader
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : BaseActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var accountHeader: AccountHeader
    private lateinit var drawer: Drawer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        setSupportActionBar(main_toolbar)

        MyApplication.appComponent.inject(this)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.getListAccount().observe(this, Observer { array ->
            addProfilesToHeader(array)
        })
        viewModel.getActiveProfile().observe(this, Observer { authStat ->
            updateAccount(authStat)
        })

        accountHeader = AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .withOnAccountHeaderListener { view, profile, current ->
                    false
                }
                .build()


        drawer = DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(accountHeader)
                .withToolbar(main_toolbar)
                .withTranslucentStatusBar(false)
                .withActionBarDrawerToggle(true)
                .addDrawerItems(
                        //pass your items here
                )
                .build()

    }

    private fun addProfilesToHeader(array: Array<Account>?) {
        if (array != null) {
            /*
            for (googleAccount in AccountUtils.getArrayGoogleSignInAccount(array)) {


                val profileDrawerItem = AccountUtils.getProfileDrawerItem(googleAccount)

                accountHeader.addProfile(profileDrawerItem, accountHeader.profiles.size)

                if (FirebaseAuth.getInstance().currentUser?.email == googleAccount.email) {
                    viewModel.setActiveProfile(googleAccount)
                }
            }
            */
            if (array.isNotEmpty()) {
                viewModel.setActiveProfile(array[0].authStatJSON)
            } else {
                Toast.makeText(this,"Array is empty",Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun updateAccount(authStat: String?) {

        Toast.makeText(this, "AuthStat: $authStat", Toast.LENGTH_LONG).show()

    }

}
