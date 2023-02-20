package com.ikhandriuk.entsoeemeterbizapp.api

import com.ikhandriuk.entsoeemeterbizapp.model.AuthorizationItem
import com.ikhandriuk.entsoeemeterbizapp.model.LogOutItem
import com.ikhandriuk.entsoeemeterbizapp.model.ParametersItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EmeterApi {
    @GET("ev_auth.php")
    suspend fun setAuthorization(
        @Query("login") login: String,
        @Query("pass") pass: String
    ): Response<AuthorizationItem>

    @GET("ev_auth")
    suspend fun logOut(@Query("deauth") code: String): Response<LogOutItem>

    @GET("deviation.asdlf?")
    fun getData(
        @Query("code") code: String,
        @Query("notlast") notlast: String,
        @Query("action") action: String,
        @Query("date") date: String,
        @Query("ids") ids: String,
        @Query("ids") fast: Boolean,
        @Query("time") time: String,
    ): Call<ParametersItem>

}