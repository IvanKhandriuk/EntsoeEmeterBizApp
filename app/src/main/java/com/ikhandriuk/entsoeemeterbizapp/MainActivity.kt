package com.ikhandriuk.entsoeemeterbizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private lateinit var todayText:TextView
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

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


    }

}