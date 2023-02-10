package com.ikhandriuk.entsoeemeterbizapp.Api

import com.ikhandriuk.entsoeemeterbizapp.Util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitDataInstance {
    private val retrofitData by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.DATA_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val APIData: EmeterApi by lazy {
        retrofitData.create(EmeterApi::class.java)
    }
}