package com.example.zodiac

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.launch

private const val TAG = "Aradito"

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: RecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val list = listOf(
            "Aquarius",
            "Aries",
            "Cancer",
            "Capricorn",
            "Gemini",
            "Leo",
            "Libra",
            "Pisces",
            "Sagittarius",
            "Scorpio",
            "Taurus",
            "Virgo"
        )

        database()

        lifecycleScope.launch {
            try {
                val response = HoroscopeApi.retrofitService.getHoroscope()
                adapter = RecyclerViewAdapter(list, response)
                recyclerView.adapter = adapter
                Log.d(TAG, "Response received: $response")
            } catch (ex: Exception) {
                Log.d(TAG, "Failed to fetch Horoscope Items", ex)
            }
        }

    }

    private fun database() {
        val db = Room.databaseBuilder(
            applicationContext,
            ZodiacDatabase::class.java, "zodiac-db"
        ).build()

        val zodiacList = listOf(
            Zodiac(
                name = "Aries",
                description = "Courageous and Energetic.",
                symbol = "Ram",
                month = "April"
            ),
            Zodiac(
                name = "Taurus",
                description = "Reliable, practical, ambitious and sensual.",
                symbol = "Bull",
                month = "May"
            ),
            Zodiac(
                name = "Gemini",
                description = "Gemini-born are clever and intellectual.",
                symbol = "Twins",
                month = "June"
            ),
            Zodiac(
                name = "Cancer",
                description = "Tenacious, loyal and sympathetic.",
                symbol = "Crab",
                month = "July"
            ),
            Zodiac(
                name = "Leo",
                description = "Warm, action-oriented, desire to be loved and admired.",
                symbol = "Lion",
                month = "August"
            ),
            Zodiac(
                name = "Virgo",
                description = "Methodical, meticulous, analytical and mentally astute.",
                symbol = "Virgin",
                month = "September"
            ),
            Zodiac(
                name = "Libra",
                description = "Maintaining balance and harmony.",
                symbol = "Scales",
                month = "October"
            ),
            Zodiac(
                name = "Scorpio",
                description = "Strong willed and mysterious.",
                symbol = "Scorpion",
                month = "November"
            ),
            Zodiac(
                name = "Sagittarius",
                description = "Born adventurers.",
                symbol = "Archer",
                month = "December"
            ),
            Zodiac(
                name = "Capricorn",
                description = "The most determined sign in the Zodiac.",
                symbol = "Goat",
                month = "January"
            ),
            Zodiac(
                name = "Aquarius",
                description = "Humanitarians to the core.",
                symbol = "Water Bearer",
                month = "February"
            ),
            Zodiac(
                name = "Pisces",
                description = "Proverbial dreamers of the Zodiac.",
                symbol = "Fish",
                month = "March"
            )
        )

        lifecycleScope.launch {
            // Delete the table
            db.zodiacDao().deleteTable()

            for (zodiac in zodiacList) {
                db.zodiacDao().insert(zodiac)
                Log.d(TAG, "database: $zodiac")
            }
        }
    }
}