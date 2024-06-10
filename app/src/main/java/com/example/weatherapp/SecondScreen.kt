package com.example.weatherapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SecondScreen : AppCompatActivity() {
    private lateinit var inputDate: EditText
    private lateinit var inputMinTemp: EditText
    private lateinit var inputMaxTemp: EditText
    private lateinit var inputWeatherCondition: EditText
    private lateinit var averageTemperature: TextView

    private val dates = mutableListOf<String>()
    private val minTemps = mutableListOf<Int>()
    private val maxTemps = mutableListOf<Int>()
    private val weatherConditions = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_screen)

        inputDate = findViewById(R.id.input_date)
        inputMinTemp = findViewById(R.id.input_min_temp)
        inputMaxTemp = findViewById(R.id.input_max_temp)
        inputWeatherCondition = findViewById(R.id.input_weather_condition)
        averageTemperature = findViewById(R.id.average_temperature)

        val btnAddData: Button = findViewById(R.id.btn_add_data)
        val btnViewDetails: Button = findViewById(R.id.btn_view_details)
        val btnClearData: Button = findViewById(R.id.btn_clear_data)
        val btnExit: Button = findViewById(R.id.btn_exit_main)

        btnAddData.setOnClickListener {
            addData()
        }

        btnViewDetails.setOnClickListener {
            viewDetails()
        }

        btnClearData.setOnClickListener {
            clearData()
        }

        btnExit.setOnClickListener {
            finish()
        }
    }

    private fun addData() {
        val date = inputDate.text.toString()
        val minTemp = inputMinTemp.text.toString().toIntOrNull()
        val maxTemp = inputMaxTemp.text.toString().toIntOrNull()
        val weatherCondition = inputWeatherCondition.text.toString()

        if (date.isNotEmpty() && minTemp != null && maxTemp != null && weatherCondition.isNotEmpty()) {
            dates.add(date)
            minTemps.add(minTemp)
            maxTemps.add(maxTemp)
            weatherConditions.add(weatherCondition)

            calculateAverageTemperature()

            inputDate.text.clear()
            inputMinTemp.text.clear()
            inputMaxTemp.text.clear()
            inputWeatherCondition.text.clear()
        } else {
            Toast.makeText(this, "Please enter valid data.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun calculateAverageTemperature() {
        val totalTemp = minTemps.sum() + maxTemps.sum()
        val count = minTemps.size + maxTemps.size
        val average = if (count > 0) totalTemp / count else 0
        averageTemperature.text = "Average Temperature: $average°C"
    }

    private fun clearData() {
        dates.clear()
        minTemps.clear()
        maxTemps.clear()
        weatherConditions.clear()
        averageTemperature.text = "Average Temperature: "
        Toast.makeText(this, "Data successfully cleaned.", Toast.LENGTH_SHORT).show()
    }

    private fun viewDetails() {
        val intent = Intent(this, ThirdScreen::class.java)
        intent.putStringArrayListExtra("dates", ArrayList(dates))
        intent.putIntegerArrayListExtra("minTemps", ArrayList(minTemps))
        intent.putIntegerArrayListExtra("maxTemps", ArrayList(maxTemps))
        intent.putStringArrayListExtra("weatherConditions", ArrayList(weatherConditions))
        startActivity(intent)
    }
}