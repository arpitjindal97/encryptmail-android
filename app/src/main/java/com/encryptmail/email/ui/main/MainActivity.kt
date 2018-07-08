package com.encryptmail.email.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.encryptmail.email.MyApplication
import com.encryptmail.email.R
import com.encryptmail.email.data.db.Account
import com.encryptmail.email.ui.base.BaseActivity
import com.encryptmail.email.utils.AccountUtils
import com.mikepenz.materialdrawer.AccountHeader
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import kotlinx.android.synthetic.main.main_activity.*
import javax.inject.Inject

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
        viewModel.getListAccount()
                .observe(this, Observer { array ->
                    updateHeader(array)
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

    private fun updateHeader(array: Array<Account>?) {
        if (array != null) {
            for (profile in AccountUtils.getArrayProfileDrawerItem(array)) {
                Toast.makeText(this, "email: " + profile.email, Toast.LENGTH_LONG).show()
                accountHeader.addProfile(profile, accountHeader.profiles.size)
            }
        }

    }
}