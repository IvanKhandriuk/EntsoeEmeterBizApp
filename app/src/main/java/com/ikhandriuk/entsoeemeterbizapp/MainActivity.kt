package com.ikhandriuk.entsoeemeterbizapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var barList: java.util.ArrayList<BarEntry>
    lateinit var barDataSet: BarDataSet
    lateinit var barData: BarData

//    private lateinit var todayText:TextView
//    private lateinit var tabLayout: TabLayout
//    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            barList = ArrayList()
            barList.add(BarEntry(1f, 300f))
            barList.add(BarEntry(2f, 450f))
            barList.add(BarEntry(3f, 150f))
            barList.add(BarEntry(4f, 200f))
            barList.add(BarEntry(5f, 600f))
            barList.add(BarEntry(6f, 450f))
            barList.add(BarEntry(7f, 800f))
            barList.add(BarEntry(8f, 550f))
            barDataSet = BarDataSet(barList, "Energy")
            barData = BarData(barDataSet)
            barChart.data=barData
            barDataSet.setColors(ColorTemplate.JOYFUL_COLORS,250)
            barDataSet.valueTextColor= Color.BLACK
            barDataSet.valueTextSize=15f

//        tabLayout=findViewById(R.id.tabLayout)
//        viewPager=findViewById(R.id.viewPager)
//        tabLayout.setupWithViewPager(viewPager)
//
//        val adapter =VPAdapter(supportFragmentManager)
//        adapter.addFragment(FragmentToday(), title= "Сьогодні")
//        adapter.addFragment(FragmentYesterday(), title = "Вчора")
//        adapter.addFragment(FragmentMonth(), title = "Січень")
//        adapter.addFragment(FragmentYear(), title = "2023")
//        viewPager.adapter=adapter


    }

}