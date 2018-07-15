package com.encryptmail.email.data.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.encryptmail.email.data.db.model.UserInfo

@Entity
open class ActiveAccount(
        var email: String,
        var userInfo: UserInfo) {

    @PrimaryKey
    var id = 0
}

@Dao
interface ActiveAccountDao {

    @Insert(onConflict = REPLACE)
    fun updateAccount(activeAccount: ActiveAccount)

    @Query("select * from activeaccount limit 1")
    fun getActiveAccount(): LiveData<ActiveAccount>

}
