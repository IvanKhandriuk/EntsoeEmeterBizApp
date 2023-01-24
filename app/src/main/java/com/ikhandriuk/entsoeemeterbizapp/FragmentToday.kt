package com.ikhandriuk.entsoeemeterbizapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import java.util.ArrayList


class FragmentToday : Fragment() {
        lateinit var barList: ArrayList<BarEntry>
        lateinit var barDataSet: BarDataSet
        lateinit var barData: BarData
        lateinit var barChart: RelativeLayout
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {

            barList = ArrayList()
            barList.add(BarEntry(10f, 500f))
            barList.add(BarEntry(20f, 500f))
            barList.add(BarEntry(30f, 500f))
            barList.add(BarEntry(40f, 500f))
            barList.add(BarEntry(50f, 500f))
            barList.add(BarEntry(60f, 500f))
            barList.add(BarEntry(70f, 500f))
            barList.add(BarEntry(80f, 500f))
            barDataSet = BarDataSet(barList, "Population")
            barData = BarData(barDataSet)
            return inflater.inflate(R.layout.fragment_today, container, false)
        }
    }

