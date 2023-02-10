package com.ikhandriuk.entsoeemeterbizapp.Api

import com.ikhandriuk.entsoeemeterbizapp.Util.Constants
import com.ikhandriuk.entsoeemeterbizapp.Util.Constants.Companion.BASE_URL
import com.ikhandriuk.multiplescreensapp.Model.AuthorizationItem
import com.ikhandriuk.multiplescreensapp.Model.LogOutItem
import com.ikhandriuk.multiplescreensapp.Model.ParametersItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object RetrofitInstance {
    private val retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val API:EmeterApi by lazy{
        retrofit.create(EmeterApi::class.java)
    }

    private val retrofitData by lazy{
        Retrofit.Builder()
            .baseUrl(Constants.DATA_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val APIData: EmeterApi by lazy{
        retrofitData.create(EmeterApi::class.java)
    }
}