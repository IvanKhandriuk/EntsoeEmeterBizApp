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
import kotlinx.android.synthetic.main.fragment_yesterday.*
import java.util.ArrayList


class FragmentYesterday : Fragment() {

    private lateinit var barList: ArrayList<BarEntry>
    private lateinit var barDataSet: BarDataSet
    private lateinit var barData: BarData

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_yesterday, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        barList = ArrayList()
        barList.add(BarEntry(1f, 150f))
        barList.add(BarEntry(2f, 300f))
        barList.add(BarEntry(3f, 100f))
        barList.add(BarEntry(4f, 500f))
        barList.add(BarEntry(5f, 700f))
        barList.add(BarEntry(6f, 250f))
        barList.add(BarEntry(7f, 600f))
        barList.add(BarEntry(8f, 800f))
        barDataSet = BarDataSet(barList, "Energy")
        barData = BarData(barDataSet)
        barDataSet.setColors(ColorTemplate.JOYFUL_COLORS, 250)
        barDataSet.valueTextColor = Color.BLACK
        barDataSet.valueTextSize = 15f
        barChartYesterday.data = barData
        super.onViewCreated(view, savedInstanceState)
    }
}