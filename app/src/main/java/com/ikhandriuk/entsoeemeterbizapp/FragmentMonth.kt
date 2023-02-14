package com.ikhandriuk.entsoeemeterbizapp

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.fragment_month.*
import kotlinx.android.synthetic.main.fragment_today.*
import java.util.ArrayList


class FragmentMonth : Fragment() {

    lateinit var barList: ArrayList<BarEntry>
    lateinit var barDataSet: BarDataSet
    lateinit var barData: BarData

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_month, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        barList = ArrayList()
        barList.add(BarEntry(1f, 600f))
        barList.add(BarEntry(2f, 700f))
        barList.add(BarEntry(3f, 300f))
        barList.add(BarEntry(4f, 500f))
        barList.add(BarEntry(5f, 100f))
        barList.add(BarEntry(6f, 350f))
        barList.add(BarEntry(7f, 900f))
        barList.add(BarEntry(8f, 300f))
        barDataSet = BarDataSet(barList, "Energy")
        barData = BarData(barDataSet)
        barDataSet.setColors(ColorTemplate.JOYFUL_COLORS, 250)
        barDataSet.valueTextColor = Color.BLACK
        barDataSet.valueTextSize = 15f
        barChartMonth.data = barData
        super.onViewCreated(view, savedInstanceState)
    }
}