package com.example.testapplication.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "additional_info")
data class AdditionalInfo(
    @PrimaryKey(autoGenerate = true) val additionalInfoId: Int = 0,
    val length: Double,
    val breadth: Double,
    val height: Double,
    val dimensionUnit: String,
    val weight: Double,
    val weightUnit: String,
    val mpn: String,
    val isbn: String,
    val upc: String
)