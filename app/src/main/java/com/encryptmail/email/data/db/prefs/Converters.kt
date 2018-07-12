package com.encryptmail.email.data.db.prefs

import android.arch.persistence.room.TypeConverter
import com.encryptmail.email.data.db.model.UserInfo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun userInfoToString(userInfo: UserInfo): String {
        return Gson().toJson(userInfo)
    }

    @TypeConverter
    fun stringToUserInfo(string: String): UserInfo {
        return Gson().fromJson(string,UserInfo::class.java)
    }
}