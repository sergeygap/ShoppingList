package com.sergey_gap.shoppinglist.activities

import android.app.Application
import com.sergey_gap.shoppinglist.dataBase.MainDataBase

class MainApp : Application() {
    val database by lazy { MainDataBase.getDataBaseInstance(this) }// by lazy запускается единожды
}