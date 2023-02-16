package com.ikhandriuk.entsoeemeterbizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.ikhandriuk.entsoeemeterbizapp.Api.EmeterApi
import com.ikhandriuk.entsoeemeterbizapp.Screens.LogInEnergyVision
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

    private lateinit var adapterItems: ArrayAdapter<String>

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