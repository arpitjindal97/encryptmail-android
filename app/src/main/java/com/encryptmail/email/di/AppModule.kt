package com.encryptmail.email.di

import android.content.Context
import com.encryptmail.email.MyApplication
import com.encryptmail.email.data.Repository
import com.encryptmail.email.data.db.AccountDao
import com.encryptmail.email.data.db.ActiveAccountDao
import com.encryptmail.email.data.network.api.ApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(app: MyApplication): Context = app


    @Provides
    @Singleton
    fun provideRepository(accountDao: AccountDao,
                          activeAccountDao: ActiveAccountDao,
                          executor: Executor,
                          apiService: ApiService): Repository =
            Repository(accountDao, activeAccountDao,executor, apiService)

    @Provides
    @Singleton
    fun provideApiService(): ApiService =
            Retrofit.Builder()
                    .baseUrl("https://www.googleapis.com")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiService::class.java)
}