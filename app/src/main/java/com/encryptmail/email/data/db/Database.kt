package com.encryptmail.email.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [(Account::class)], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
}