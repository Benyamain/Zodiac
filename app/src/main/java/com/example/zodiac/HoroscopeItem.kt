package com.example.zodiac

import androidx.lifecycle.GeneratedAdapter
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HoroscopeItem(
    val sign: String,
    val title: String
)