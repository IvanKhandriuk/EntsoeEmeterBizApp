package com.ikhandriuk.entsoeemeterbizapp.model.parameters

import com.google.gson.JsonArray

data class ParamItem(
    val type:Int=0,
    val ddata: ArrayList<JsonArray>?,
    val bdata: ArrayList<JsonArray>?)
