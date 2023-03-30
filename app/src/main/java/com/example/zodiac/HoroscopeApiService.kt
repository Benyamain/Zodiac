package com.example.zodiac

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://zodiac-api-test.onrender.com/"

/**
 * Build the Moshi object with Kotlin adapter factory that Retrofit will be using.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * The Retrofit object with the Moshi converter.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface HoroscopeApiService {
    @GET("horoscopes")
    suspend fun getHoroscope() : List<HoroscopeItem>
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object HoroscopeApi {
    val retrofitService: HoroscopeApiService by lazy { retrofit.create(HoroscopeApiService::class.java) }
}
