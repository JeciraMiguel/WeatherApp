package com.example.weatherapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ThirdScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third_screen)

        val listView: ListView = findViewById(R.id.list_view)
        val btnBack: Button = findViewById(R.id.btn_back)

        val dates = intent.getStringArrayListExtra("dates")
        val minTemps = intent.getIntegerArrayListExtra("minTemps")
        val maxTemps = intent.getIntegerArrayListExtra("maxTemps")
        val weatherConditions = intent.getStringArrayListExtra("weatherConditions")

        val detailsList = ArrayList<String>()
        for (i in dates!!.indices) {
            val detail = "Date: ${dates[i]}, Min Temp: ${minTemps!![i]}°C, Max Temp: ${maxTemps!![i]}°C, Condition: ${weatherConditions!![i]}"
            detailsList.add(detail)
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, detailsList)
        listView.adapter = adapter

        btnBack.setOnClickListener {
            finish()
        }
    }
}