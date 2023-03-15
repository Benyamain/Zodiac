package com.example.zodiac

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "zodiac_signs")
data class Zodiac(
    @PrimaryKey val name: String,
    val description: String,
    val symbol: String,
    val month: String
)
