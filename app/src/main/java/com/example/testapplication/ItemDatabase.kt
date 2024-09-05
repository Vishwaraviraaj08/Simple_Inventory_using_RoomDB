package com.example.testapplication

import com.example.testapplication.Model.StockInfo
import com.example.testapplication.Model.AdditionalInfo
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.testapplication.Model.Item


@Database(entities = [Item::class, StockInfo::class, AdditionalInfo::class], version = 1)
abstract class ItemDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao

    companion object {
        
        private var instance: ItemDatabase? = null;

        fun createDatabase(context: Context): ItemDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    ItemDatabase::class.java,
                    "items"
                ).build()
            }
            return instance as ItemDatabase
        }
        
    }


}