package com.ikhandriuk.entsoeemeterbizapp.repository

import com.ikhandriuk.entsoeemeterbizapp.api.RetrofitInstance
import com.ikhandriuk.entsoeemeterbizapp.model.AuthorizationItem
import com.ikhandriuk.entsoeemeterbizapp.model.LogOutItem
import retrofit2.Response

class Repository {

    suspend fun setAuthorization(login: String, pass: String): Response<AuthorizationItem> {
        return RetrofitInstance.API.setAuthorization(login, pass)
    }

    suspend fun logOut(authorizationCode: String): Response<LogOutItem> {
        return RetrofitInstance.API.logOut(authorizationCode)
    }

}