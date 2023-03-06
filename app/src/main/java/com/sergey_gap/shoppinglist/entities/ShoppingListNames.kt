package com.sergey_gap.shoppinglist.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "shopping_list_names")
//одна сущность, одна таблица
data class ShoppingListNames(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    @ColumnInfo(name = "name") // название колонки с списками дел
    val name: String,
    @ColumnInfo(name = "time") // время, когда нужно выполнить список, либо дата создания списка
    val time: String,
    @ColumnInfo(name = "allItemCounter") // все пункты списка
    val allItemCount: Int,
    @ColumnInfo(name = "checkedItemsCounter") // счетчик уже отмеченных элементов
    val checkedItemsCounter: Int,
    @ColumnInfo(name = "ItemsIds") // id каждого элемента списка
    val ItemsIds: Int

): Serializable
