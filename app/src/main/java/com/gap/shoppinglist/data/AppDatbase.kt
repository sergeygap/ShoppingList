package com.gap.shoppinglist.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ShopItemDBEntity::class], version = 2, exportSchema = true)
abstract class AppDatbase : RoomDatabase() {

    abstract fun shopItemDao(): ShopItemDao

    companion object {
        private var INSTANCE: AppDatbase? = null
        private var LOCK = Any()
        private const val DB_NAME = "shop_item.db"

        fun getInstance(application: Application): AppDatbase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    application,
                    AppDatbase::class.java,
                    DB_NAME
                )
                    .allowMainThreadQueries()
                    .build()
                LOCK = db
                return db
            }
        }

    }

}