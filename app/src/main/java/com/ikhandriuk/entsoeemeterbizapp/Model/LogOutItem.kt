package com.ikhandriuk.multiplescreensapp.Model

import okhttp3.Headers
import retrofit2.http.Header

data class LogOutItem(
    val code: String = "",
    val result: String = ""
)
