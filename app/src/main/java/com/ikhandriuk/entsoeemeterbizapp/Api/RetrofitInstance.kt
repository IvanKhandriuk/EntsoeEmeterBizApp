package com.ikhandriuk.entsoeemeterbizapp.Api

import com.ikhandriuk.multiplescreensapp.Model.AuthorizationItem
import com.ikhandriuk.multiplescreensapp.Model.LogOutItem
import com.ikhandriuk.multiplescreensapp.Model.ParametersItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

object RetrofitInstance {
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
            @Query("time") time: String,
        ): Call<ParametersItem>

        @GET("deviation.asdlf?")
        fun getDatta(
            @Query("code") code: String,
            @Query("notlast") notlast: String,
            @Query("action") action: String,
            @Query("date") date: String,
            @Query("ids") ids: String,
            @Query("time") time: String,
        ): Response<ParametersItem>

    }
}