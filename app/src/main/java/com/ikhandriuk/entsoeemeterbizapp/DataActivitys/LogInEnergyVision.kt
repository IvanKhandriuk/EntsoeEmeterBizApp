package com.ikhandriuk.entsoeemeterbizapp.DataActivitys

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.ikhandriuk.entsoeemeterbizapp.MainActivity
import com.ikhandriuk.entsoeemeterbizapp.MainViewModel
import com.ikhandriuk.entsoeemeterbizapp.MainViewModelFactory
import com.ikhandriuk.entsoeemeterbizapp.Repository.Repository
import java.text.SimpleDateFormat
import java.util.*

class LogInEnergyVision: AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

     fun authorization(){
        val repository = Repository()
        val viewModelFactory= MainViewModelFactory(repository)
        viewModel= ViewModelProvider(this,viewModelFactory)[MainViewModel::class.java]

        val myLogIn = "ivan"
        val myPassWord = "ivan1234"
        val encodePass: String = Base64.getEncoder().encodeToString(myPassWord.toByteArray())

        viewModel.setAuthorization(myLogIn, encodePass)
        viewModel.myAuthResponse.observe(
            this, Observer{ response ->
                if (response.isSuccessful&&response.body()?.code.toString()!="0") {
                    val authCode = response.body()?.code.toString()
                    Log.d("AuthCode", "code is $authCode")
                    Log.d("Response result", response.body()?.result.toString())
                    val intent= Intent(this@LogInEnergyVision, MainActivity::class.java)
                    intent.putExtra("AuthCode",authCode)
                } else {
                    Log.d("Error", response.errorBody().toString())
                    Toast.makeText(this@LogInEnergyVision,"Wrong password or login", Toast.LENGTH_SHORT).show()
                }
            })
    }
}