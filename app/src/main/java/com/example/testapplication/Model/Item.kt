package com.example.testapplication.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Embedded

@Entity(tableName = "items")
data class Item(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val sellingPrice: Double,
    val costPrice: Double,
    val quantity: Double,
    @Embedded val stockSummary: StockInfo,
    val itemAccount: String,
    val openingStock: Double,
    val reorderLevel: Double,
    @Embedded val additionalInfo: AdditionalInfo? = null
)
