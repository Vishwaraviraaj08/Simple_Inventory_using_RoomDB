package com.example.testapplication

import android.app.Application
import com.example.testapplication.Model.Item


class ItemViewModel(application : Application) {
    private var itemDao: ItemDao
    private var dbInstance: ItemDatabase = ItemDatabase.createDatabase(application)

    init {
        itemDao = dbInstance.itemDao()
    }

    suspend fun insertItem(item: Item) {
        itemDao.insertItem(item)
    }

    suspend fun getItems(): List<Item> {
        return itemDao.getItems()
    }


}




