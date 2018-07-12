package com.encryptmail.email.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.encryptmail.email.data.db.prefs.Converters

@Database(entities = [(Account::class)], version = 1)

@TypeConverters(Converters::class)

abstract class MyDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
}