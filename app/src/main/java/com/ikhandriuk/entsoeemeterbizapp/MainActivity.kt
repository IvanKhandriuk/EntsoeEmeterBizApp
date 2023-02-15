package com.ikhandriuk.entsoeemeterbizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.VERBOSE
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.ikhandriuk.entsoeemeterbizapp.Api.EmeterApi
import com.ikhandriuk.entsoeemeterbizapp.DataActivitys.LogInEnergyVision
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
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    lateinit var viewModel: MainViewModel

    private val date = SimpleDateFormat("yyyy-MM-dd")
    private val calendar = Calendar.getInstance()
    private val currentDate = date.format(calendar.time)
    private val nanoTime = calendar.timeInMillis.toString()

    private lateinit var adapterItems: ArrayAdapter<String>

   private lateinit var energyCode:LogInEnergyVision

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager=findViewById(R.id.viewPager)
        tabLayout=findViewById(R.id.tabLayout)
        tabLayout.setupWithViewPager(viewPager)

        val adapter =VPAdapter(supportFragmentManager)
        adapter.addFragment(FragmentToday(), title= "Сьогодні")
        adapter.addFragment(FragmentYesterday(), title = "Вчора")
        adapter.addFragment(FragmentMonth(), title = "Лютий")
        adapter.addFragment(FragmentYear(), title = "2023")
        viewPager.adapter=adapter

        val repository = Repository()
        val viewModelFactory= MainViewModelFactory(repository)
        viewModel= ViewModelProvider(this,viewModelFactory)[MainViewModel::class.java]

        energyCode.authorization()


        val authCode = intent.getStringExtra("AuthCode").toString()
        Log.d("GET code",authCode)
        //getMyData(authCode, "1", "data", currentDate, "", nanoTime)

        val stationsNames:String="Київська ГАЕС,Київська ГЕС,Канівська ГЕС," +
                "Кременчуцька ГЕС,Середньодніпровська ГЕС,ДніпроГЕС-1,ДніпроГЕС-2," +
                "Каховська ГЕС,Дністровська ГЕС,Дністровська ГАЕС,ПрАТ «Укргідроенерго»," +
                "ПрАТ «Укргідроенерго» ГЕС,ПрАТ «Укргідроенерго» ГАЕС,Нижньодністровська ГЕС," +
                "Краснооскільська ГЕС,Сумська ТЕЦ,Дарницька ТЕЦ,Черкаська ТЕЦ,Чернігівська ТЕЦ," +
                "ТОВ ТЕПЛОЕНЕРГО ГРУП,Дніпровська ТЕЦ,Охтирська ТЕЦ,Білоцерківська ТЕЦ," +
                "Орель-Лідер,Атлас Энерджи 1,Атлас Энерджи 2,Новопоселкова СЕС," +
                "Новоолександрівська СЕС,Солар Квант 1,Солар Квант 2,Вільногірська СЕС," +
                "Улянівська СЕС,РУФ СЕС,ТОВ ІМПЕРІАЛ ЕНЕРГО,ТОВ ІМПЕРІАЛ ЕНЕРГО 1 черга," +
                "ТОВ ІМПЕРІАЛ ЕНЕРГО 2 черга,Водяне СЕС,Сирово СЕС,Олешківська СЕС," +
                "Піщанка СЕС 1,Піщанка СЕС 2,НІКОПОЛЬ ЕЛІОС,БОЛГРАД ЕЛІОС,ПРОМЕТЕЙ ЕТГ," +
                "ЧЕРВОНА ГОРА ЕКО ФЕС 1,ЧЕРВОНА ГОРА ЕКО ФЕС 2,ФЕС КИРЯКІВКА,ПП КАСМЕТ," +
                "ЮСГ Виноградове,АСТЕРІЯ СОЛАР,ТАВАНЬ СОЛАР 3,ПРИМА СОЛАР ЕНЕРДЖІ,ТАВАНЬ СОЛАР 2," +
                "ТАВАНЬ СОЛАР 1,НІКЕЛІОС,НЗФ,КП ДОР,Аульський водовід"
        val listABC:List<String> = listOf(*stationsNames.split(",").toTypedArray())

        adapterItems=ArrayAdapter(this,R.layout.list_item,listABC)

        auto_complete_txt.setAdapter(adapterItems)
        auto_complete_txt.setOnItemClickListener { adapterView, view, i, l ->
            adapterView.getItemAtPosition(i).toString()
            Toast.makeText(applicationContext, "Item:", Toast.LENGTH_SHORT).show()
        }
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
            .baseUrl(DATA_URL)
            .build()
            .create(EmeterApi::class.java)

        val retrofitData = retrofitBuilder.getData(code, notlast, action, date, ids, time)

        retrofitData.enqueue(object : Callback<ParametersItem?> {
            override fun onResponse(call: Call<ParametersItem?>, response: Response<ParametersItem?>) {
                if(!response.isSuccessful){
                    Toast.makeText(this@MainActivity,response.code().toString(),Toast.LENGTH_LONG).show()
                    return
                }
                Log.d("StationNames", dataToNames(response.body()?.data).toString())
                intent.putExtra("StationNames",response.body()?.data.toString())
                return
            }

            override fun onFailure(call: Call<ParametersItem?>, t: Throwable) {
                Log.d("CurrentError", "onFailure: " + t.message)
                Toast.makeText(this@MainActivity,t.message,Toast.LENGTH_LONG).show()
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val authCode = intent.getStringExtra("RsCode").toString()
        val repository = Repository()
        val secondViewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, secondViewModelFactory)[MainViewModel::class.java]
        Log.d("RsCode", authCode)
        if (item.itemId == R.id.logout) {
            viewModel.logOut(authCode)
            viewModel.logOutResponse.observe(this, Observer {response ->
                if (response.isSuccessful){
                    Log.d("Answer_code", response.code().toString())
                } else {
                    Log.d("Answer_error", response.errorBody().toString())
                }
            })
            finish()
            return true
        }
        return true
    }

}