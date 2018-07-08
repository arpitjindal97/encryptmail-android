package com.encryptmail.email.data.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE


@Entity
open class Account constructor(
        var email: String,
        var googleAccount: String) {

    @PrimaryKey
    var id: Int = 0
}

@Dao
interface AccountDao {

    @Insert(onConflict = REPLACE)
    fun insertAccount(account: Account)

    @Query("select * from account where email = :email")
    fun getAccount(email: String): LiveData<Account>

    @Query("select * from account")
    fun getAllAccount(): LiveData<Array<Account>>
}