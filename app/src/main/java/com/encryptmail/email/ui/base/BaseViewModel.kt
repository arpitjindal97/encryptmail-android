package com.encryptmail.email.ui.base

import android.arch.lifecycle.ViewModel
import com.encryptmail.email.MyApplication

open class BaseViewModel : ViewModel() {

    init {
        MyApplication.appComponent.inject(this)
    }
}