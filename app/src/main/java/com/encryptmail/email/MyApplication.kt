package com.encryptmail.email

import android.app.Application
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import com.encryptmail.email.di.AppComponent
import com.encryptmail.email.di.AppModule
import com.encryptmail.email.di.DaggerAppComponent
import com.encryptmail.email.di.RoomModule
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader
import com.mikepenz.materialdrawer.util.DrawerImageLoader
import com.squareup.picasso.Picasso


class MyApplication : Application() {

    companion object {

        lateinit var appComponent: AppComponent

    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
                .builder()
                .application(this)
                .build()

        appComponent.inject(this)

        android.os.Handler().postDelayed({
            initializeDrawerImageLoader()
        }, 1000)


        initializeDrawerImageLoader()
    }

    private fun initializeDrawerImageLoader() {
        DrawerImageLoader.init(object : AbstractDrawerImageLoader() {
            override fun set(imageView: ImageView, uri: Uri, placeholder: Drawable) {
                super.set(imageView, uri, placeholder)
                Picasso.get().load(uri).into(imageView)
            }

            override fun cancel(imageView: ImageView) {
                super.cancel(imageView)
                Picasso.get().cancelRequest(imageView)
            }
        })
    }
}