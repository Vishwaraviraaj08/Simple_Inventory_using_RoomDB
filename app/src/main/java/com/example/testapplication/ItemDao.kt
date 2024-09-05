package com.example.testapplication;


import androidx.room.*
import com.example.testapplication.Model.Item

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: Item)

    @Query("SELECT * FROM items")
    suspend fun getItems(): List<Item>

}


