package com.encryptmail.email.data.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.encryptmail.email.data.db.model.UserInfo


@Entity
open class Account constructor(
        @PrimaryKey var email: String,
        var userInfo: UserInfo,
        var authStateJSON: String)


@Dao
interface AccountDao {

    @Insert(onConflict = REPLACE)
    fun insertAccount(account: Account)

    @Query("select * from account where email = :email")
    fun getAccount(email: String): LiveData<Account>

    @Query("select * from account")
    fun getAllAccount(): LiveData<Array<Account>>
}