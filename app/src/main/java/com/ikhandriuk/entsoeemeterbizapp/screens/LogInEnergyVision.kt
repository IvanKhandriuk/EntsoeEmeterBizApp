package com.ikhandriuk.entsoeemeterbizapp.screens

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ikhandriuk.entsoeemeterbizapp.MainViewModel
import com.ikhandriuk.entsoeemeterbizapp.MainViewModelFactory
import com.ikhandriuk.entsoeemeterbizapp.repository.Repository
import java.util.*

class LogInEnergyVision: AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        val myLogIn = "ivan"
        val myPassWord = "ivan1234"
        val encodePass: String = Base64.getEncoder().encodeToString(myPassWord.toByteArray())

        viewModel.setAuthorization(myLogIn, encodePass)
        viewModel.myAuthResponse.observe(
            this
        ) { response ->
            if (response.isSuccessful && response.body()?.code.toString() != "0") {
                val authCode = response.body()?.code.toString()
                Log.d("AuthCode", "code is $authCode")
                Log.d("Response result", response.body()?.result.toString())
                val intent = Intent(this@LogInEnergyVision, GetData::class.java)
                intent.putExtra("AuthCode", authCode)
                finish()
                startActivity(intent)
            } else {
                Log.d("Error", response.errorBody().toString())
                Toast.makeText(
                    this@LogInEnergyVision,
                    "Wrong password or login",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}