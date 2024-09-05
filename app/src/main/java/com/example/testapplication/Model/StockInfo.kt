package com.example.testapplication.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stock_info")
data class StockInfo(
    @PrimaryKey(autoGenerate = true) val stockId: Int = 0,
    val physicalStockOnHand: Double,
    val physicalCommittedStock: Double,
    val physicalAvailableForSale: Double,
    val accountingStockOnHand: Double,
    val accountingCommittedStock: Double
)
