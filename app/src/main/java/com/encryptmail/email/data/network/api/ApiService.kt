package com.encryptmail.email.data.network.api

import com.encryptmail.email.data.db.model.UserInfo
import io.reactivex.Flowable
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Url

interface ApiService {

    @POST
    fun getUserInfo(@Url endPoint: String,
                    @Header("Authorization") bearer: String): Flowable<UserInfo>
}