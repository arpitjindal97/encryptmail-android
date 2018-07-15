package com.encryptmail.email.di

import android.arch.persistence.room.Room
import android.content.Context
import com.encryptmail.email.data.db.AccountDao
import com.encryptmail.email.data.db.ActiveAccountDao
import com.encryptmail.email.data.db.MyDatabase
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun provideDatabase(app: Context): MyDatabase {
        return Room
                .databaseBuilder(app, MyDatabase::class.java, "data-db")
                .build()
    }

    @Singleton
    @Provides
    fun provideAccountDao(database: MyDatabase): AccountDao =
            database.accountDao()

    @Singleton
    @Provides
    fun provideExecutor(): Executor {
        return Executors.newFixedThreadPool(2)
    }

    @Singleton
    @Provides
    fun provideActiveAccountDao(database: MyDatabase): ActiveAccountDao =
            database.activeAccountDao()
}

