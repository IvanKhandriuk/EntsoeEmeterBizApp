package com.ikhandriuk.entsoeemeterbizapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.ikhandriuk.multiplescreensapp.Model.Parameters.DataItem
import com.ikhandriuk.multiplescreensapp.Model.ParametersItem
import kotlinx.android.synthetic.main.fragment_today.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
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
    }



