package com.ikhandriuk.entsoeemeterbizapp.api

import com.ikhandriuk.entsoeemeterbizapp.util.Constants
import com.ikhandriuk.entsoeemeterbizapp.util.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

}