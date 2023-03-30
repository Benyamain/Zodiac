package com.example.zodiac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import kotlinx.coroutines.launch


private const val TAG = "Aradito"

class ResultActivity : AppCompatActivity() {

    private lateinit var zodiacNameTextView: TextView
    private lateinit var zodiacSymbolTextView: TextView
    private lateinit var zodiacMonthTextView: TextView
    private lateinit var zodiacDescriptionTextView: TextView
    private lateinit var zodiacApi: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        zodiacNameTextView = findViewById(R.id.zodiac_name)
        zodiacSymbolTextView = findViewById(R.id.zodiac_symbol)
        zodiacMonthTextView = findViewById(R.id.zodiac_month)
        zodiacDescriptionTextView = findViewById(R.id.zodiac_description)
        zodiacApi = findViewById(R.id.zodiac_api)

        getDatabase()
    }

    private fun getDatabase() {
        val db = Room.databaseBuilder(
            applicationContext,
            ZodiacDatabase::class.java, "zodiac-db"
        ).build()

        val selectedZodiac = intent.getStringExtra("selectedZodiac")
        Log.d(TAG, "Selected zodiac: ${selectedZodiac?.lowercase()}")

        lifecycleScope.launch {
            val zodiac = selectedZodiac?.let { db.zodiacDao().getZodiacSignByName(it) }
            zodiacNameTextView.text = zodiac?.name
            zodiacSymbolTextView.text = zodiac?.symbol
            zodiacMonthTextView.text = zodiac?.month
            zodiacDescriptionTextView.text = zodiac?.description

            val horoscopes = HoroscopeApi.retrofitService.getHoroscope()
            for (horoscope in horoscopes) {
                if (horoscope.sign.equals(selectedZodiac, ignoreCase = true)) {
                    zodiacApi.text = horoscope.title
                    break
                }
            }
        }
    }
}