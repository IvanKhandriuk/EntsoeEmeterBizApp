package com.ikhandriuk.entsoeemeterbizapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.android.material.tabs.TabLayout
import com.ikhandriuk.entsoeemeterbizapp.Api.EmeterApi
import com.ikhandriuk.entsoeemeterbizapp.Repository.Repository
import com.ikhandriuk.entsoeemeterbizapp.Util.Constants.Companion.DATA_URL
import com.ikhandriuk.multiplescreensapp.Model.Parameters.DataItem
import com.ikhandriuk.multiplescreensapp.Model.ParametersItem
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabLayout=findViewById(R.id.tabLayout)
        viewPager=findViewById(R.id.viewPager)
        tabLayout.setupWithViewPager(viewPager)

        val adapter =VPAdapter(supportFragmentManager)
        adapter.addFragment(FragmentToday(), title= "Сьогодні")
        adapter.addFragment(FragmentYesterday(), title = "Вчора")
        adapter.addFragment(FragmentMonth(), title = "Січень")
        adapter.addFragment(FragmentYear(), title = "2023")
        viewPager.adapter=adapter

        val repository = Repository()
        val viewModelFactory= MainViewModelFactory(repository)
        viewModel= ViewModelProvider(this,viewModelFactory)[MainViewModel::class.java]

            val myLogIn = "ivan"
            val myPassWord = "ivan1234"
            val encodePass: String = Base64.getEncoder().encodeToString(myPassWord.toByteArray())
            viewModel.setAuthorization(myLogIn, encodePass)
            viewModel.myAuthResponse.observe(this, androidx.lifecycle.Observer{ response ->
                if (response.isSuccessful&&response.body()?.code.toString()!="0") {
                    Log.d("RsCode", response.body()?.code.toString())
                    Log.d("Response result", response.body()?.result.toString())
//                    intent.putExtra("RsCode",viewModel.myAuthResponse.value?.body()?.code.toString())
//                    finish()
//                    startActivity(intent)
                } else {
                    Log.d("Error", response.errorBody().toString())
                    Toast.makeText(this@MainActivity,"Wrong password or login",Toast.LENGTH_SHORT).show()
                }
            })
    }
}