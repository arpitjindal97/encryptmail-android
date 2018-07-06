package com.encryptmail.email.ui.splash

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.encryptmail.email.MyApplication
import com.encryptmail.email.ui.base.BaseViewModel
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class SplashViewModel : BaseViewModel() {

    init {
        MyApplication.appComponent.inject(this)
    }

    @Inject
    lateinit var firebaseAuth: LiveData<FirebaseAuth>


    fun getFireAuth(): LiveData<FirebaseAuth> {

        return firebaseAuth
    }
}