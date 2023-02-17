package com.ikhandriuk.entsoeemeterbizapp

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
import com.ikhandriuk.entsoeemeterbizapp.repository.Repository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var viewModel: MainViewModel

    private lateinit var adapterItems: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager=findViewById(R.id.viewPager)
        tabLayout=findViewById(R.id.tabLayout)
        tabLayout.setupWithViewPager(viewPager)

        val checkNames=intent.getStringExtra("StationNames").toString()
        Log.d("NamesTest",checkNames)

        val adapter =VPAdapter(supportFragmentManager)
        adapter.addFragment(FragmentToday(), title= "Сьогодні")
        adapter.addFragment(FragmentYesterday(), title = "Вчора")
        adapter.addFragment(FragmentMonth(), title = "Лютий")
        adapter.addFragment(FragmentYear(), title = "2023")
        viewPager.adapter=adapter

        val stationsNames=intent.getStringExtra("StationNames").toString()

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