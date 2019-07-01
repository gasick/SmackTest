package com.example.smack.Controllers

import android.app.Application
import com.example.smack.Utilities.SharedPrefs

//Подобрый класс создается до того как создаются активити для класса
class App: Application() {

    //Создаем синглтон для класса
    //предоставляет возможность иметь только лишь один экземпляр данного объекта-компаньена
    companion object{
        lateinit var prefs: SharedPrefs
    }

    override fun onCreate() {
        prefs = SharedPrefs(applicationContext)
        super.onCreate()
    }



}