package com.example.zodiac

import androidx.room.*
import java.util.*

@Dao
interface ZodiacDao {
    @Insert
    suspend fun insert(zodiac: Zodiac)

    @Query("SELECT * FROM zodiac_signs WHERE name = :name")
    suspend fun getZodiacSignByName(name: String): Zodiac?

    @Query("DELETE FROM zodiac_signs")
    suspend fun deleteTable()

}