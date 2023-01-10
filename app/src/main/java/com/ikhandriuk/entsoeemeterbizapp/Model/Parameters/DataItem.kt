package com.ikhandriuk.multiplescreensapp.Model.Parameters

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.ikhandriuk.multiplescreensapp.Model.ParametersItem
import org.json.JSONArray

data class DataItem(
    val name : String = "",
    val id : Int=0,
    val param: ArrayList<ParamItem>?)
