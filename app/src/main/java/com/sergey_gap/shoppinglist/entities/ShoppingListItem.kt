package com.sergey_gap.shoppinglist.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shop_list_item")
data class ShoppingListItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    @ColumnInfo(name = "name") // название продукта
    val name: String,
    @ColumnInfo(name = "itemInfo") // сведения о продукте(сколько грамм, какой сорт)
    val itemInfo: String,
    @ColumnInfo(name = "itemChecked") // купил или еще нет
    val itemChecked: Int = 0,
    @ColumnInfo(name = "listId") // ссылка на список
    val listId: Int,
    @ColumnInfo(name = "itemType") // необходимы тип для подсказок
    val itemType: String = "item"
)
