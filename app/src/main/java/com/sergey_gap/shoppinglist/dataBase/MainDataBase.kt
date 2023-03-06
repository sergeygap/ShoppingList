package com.sergey_gap.shoppinglist.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sergey_gap.shoppinglist.entities.LibraryItem
import com.sergey_gap.shoppinglist.entities.NoteItem
import com.sergey_gap.shoppinglist.entities.ShoppingListItem

// будет давать instance нашей бд
@Database (entities = [LibraryItem::class, NoteItem::class, ShoppingListItem::class, ShoppingListItem::class], version = 1)
abstract class MainDataBase: RoomDatabase() {

    companion object { // используется так: функции написанные в теле, для них не нужно создавать экземпляр класса
        @Volatile
        private var INSTANCE: MainDataBase? = null
        fun getDataBaseInstance(context: Context): MainDataBase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                    MainDataBase::class.java, "shopping_list.db"
                ).build()
                instance
            }
        }
    }
}
// context.applicationContext - берет контекст всего приложения