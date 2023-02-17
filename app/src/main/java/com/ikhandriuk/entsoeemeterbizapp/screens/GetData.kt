package com.ikhandriuk.entsoeemeterbizapp.screens

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.JsonArray
import com.ikhandriuk.entsoeemeterbizapp.*
import com.ikhandriuk.entsoeemeterbizapp.api.EmeterApi
import com.ikhandriuk.entsoeemeterbizapp.model.parameters.DataItem
import com.ikhandriuk.entsoeemeterbizapp.model.parameters.ParamItem
import com.ikhandriuk.entsoeemeterbizapp.model.ParametersItem
import com.ikhandriuk.entsoeemeterbizapp.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class GetData : AppCompatActivity() {

    private val date = SimpleDateFormat("yyyy-MM-dd")
    private val calendar = Calendar.getInstance()
    private val currentDate = date.format(calendar.time)
    private val nanoTime = calendar.timeInMillis.toString()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        val authCode = intent.getStringExtra("AuthCode").toString()
        getMyData(authCode, "1", "data", currentDate, "", nanoTime)

    }
    private fun getMyData(
        code: String,
        notlast: String,
        action: String,
        date: String,
        ids: String,
        time: String
    ) {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.DATA_URL)
            .build()
            .create(EmeterApi::class.java)

        val retrofitData = retrofitBuilder.getData(code, notlast, action, date, ids, time)

        retrofitData.enqueue(object : Callback<ParametersItem?> {
            override fun onResponse(call: Call<ParametersItem?>, response: Response<ParametersItem?>) {
                if(!response.isSuccessful){
                    Toast.makeText(this@GetData,response.code().toString(), Toast.LENGTH_LONG).show()
                    return
                }
                Log.d("StationId",dataToId(response.body()?.data).toString())
                val namesOfStations:String = dataToNames(response.body()?.data).toString()
                val idOfStations: String = dataToId(response.body()?.data).toString()
                val paramOfStations: String = dataToParam(response.body()?.data).toString()
                Log.d("StationsParam",paramOfStations)
                //val ddataOfParam: List<ParamItem> = dataToDData()

                val intent= Intent(this@GetData,MainActivity::class.java)
                val authCode = intent.getStringExtra("AuthCode").toString()
                intent.putExtra("AuthCode",authCode)
                intent.putExtra("StationNames",namesOfStations)
                intent.putExtra("StationId",idOfStations)
                //intent.putExtra("StationParam",paramOfStations)
                finish()
                startActivity(intent)
                return
            }

            override fun onFailure(call: Call<ParametersItem?>, t: Throwable) {
                Log.d("CurrentError", "onFailure: " + t.message)
                Toast.makeText(this@GetData,t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun dataToId(data: List<DataItem>?): List<Int> {
        var result: MutableList<Int> = arrayListOf()
        if (data != null)
            for (i in data) {
                result.add(i.id)
            }
        return result
    }

    private fun dataToNames(data: List<DataItem>?): List<String> {
        var result: MutableList<String> = arrayListOf()
        if (data != null)
            for (i in data) {
                result.add(i.name)
            }
        return result
    }

    private fun dataToParam(data: List<DataItem>?): List<ArrayList<ParamItem>> {
        var result: MutableList<ArrayList<ParamItem>> = arrayListOf()
        if (data != null)
            for (i in data) {
                result.add(i.param!!)
            }
        return result
    }

//    private fun dataToDData(data: List<ParamItem>?): List<ArrayList<JsonArray>> {
//        var result: MutableList<ArrayList<JsonArray>> = arrayListOf()
//        if (data != null)
//            for (i in data) {
//                result.add(i.ddata!!)
//            }
//        return result
//    }

}