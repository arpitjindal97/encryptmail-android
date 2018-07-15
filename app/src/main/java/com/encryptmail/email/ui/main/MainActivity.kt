package com.encryptmail.email.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.widget.Toast
import com.encryptmail.email.R
import com.encryptmail.email.data.db.Account
import com.encryptmail.email.data.db.ActiveAccount
import com.encryptmail.email.ui.base.BaseActivity
import com.mikepenz.materialdrawer.AccountHeader
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : BaseActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var accountHeader: AccountHeader
    private lateinit var drawer: Drawer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        setSupportActionBar(main_toolbar)

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


        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.getListAccount().observe(this, Observer { array ->
            addProfilesToHeader(array)
        })
        viewModel.getActiveAccount().observe(this, Observer { activeAccount ->
            if (activeAccount != null)
                updateAccount(activeAccount)
        })
    }

    private fun addProfilesToHeader(array: Array<Account>?) {
        if (array != null) {

            accountHeader.clear()
            for (account in array) {

                val profileDrawerItem = ProfileDrawerItem()
                        .withName(account.userInfo.name)
                        .withEmail(account.userInfo.email)
                        .withIcon(account.userInfo.picture)

                accountHeader.addProfiles(profileDrawerItem)

            }
        }
    }

    private fun updateAccount(activeAccount: ActiveAccount?) {

        Toast.makeText(this, "This is active account: " + activeAccount?.email, Toast.LENGTH_LONG).show()

    }

}
