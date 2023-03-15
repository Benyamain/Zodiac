package com.example.zodiac

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Zodiac::class], version = 1)
abstract class ZodiacDatabase: RoomDatabase() {
    abstract fun zodiacDao(): ZodiacDao
}