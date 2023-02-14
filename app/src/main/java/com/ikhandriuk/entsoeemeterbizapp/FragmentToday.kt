package com.ikhandriuk.entsoeemeterbizapp

import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.fragment_today.*
import java.util.*


class FragmentToday : Fragment() {
        lateinit var barList: ArrayList<BarEntry>
        lateinit var barDataSet: BarDataSet
        lateinit var barData: BarData

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.fragment_today, container, false)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

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
        barDataSet.setColors(ColorTemplate.JOYFUL_COLORS,250)
        barDataSet.valueTextColor= Color.BLACK
        barDataSet.valueTextSize=15f
        barChartToday.data=barData
        super.onViewCreated(view, savedInstanceState)
    }
    }



