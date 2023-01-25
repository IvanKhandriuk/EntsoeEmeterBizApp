package com.ikhandriuk.entsoeemeterbizapp

import android.graphics.Color
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
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.fragment_today.*
import java.util.ArrayList


class FragmentToday : Fragment() {
        lateinit var barList: ArrayList<BarEntry>
        lateinit var barDataSet: BarDataSet
        lateinit var barData: BarData
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {

            barList = ArrayList()
            barList.add(BarEntry(1f, 500f))
            barList.add(BarEntry(2f, 500f))
            barList.add(BarEntry(3f, 500f))
            barList.add(BarEntry(4f, 500f))
            barList.add(BarEntry(5f, 500f))
            barList.add(BarEntry(6f, 500f))
            barList.add(BarEntry(7f, 500f))
            barList.add(BarEntry(8f, 500f))
            barDataSet = BarDataSet(barList, "Population")
            barData = BarData(barDataSet)
            barChart.data=barData
            barDataSet.setColors(ColorTemplate.JOYFUL_COLORS,250)
            barDataSet.valueTextColor= Color.BLACK
            barDataSet.valueTextSize=15f

            return inflater.inflate(R.layout.fragment_today, container, false)
        }
    }



