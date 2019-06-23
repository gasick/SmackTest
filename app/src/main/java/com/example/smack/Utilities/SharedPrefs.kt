package com.example.smack.Utilities

import android.content.Context
import com.android.volley.toolbox.Volley

class SharedPrefs(context: Context) {

    val PREFS_FILENAME = "prefs"
    //задаем параметры(имя файла, режим 0 - что значит приватный)
    val prefs = context.getSharedPreferences(PREFS_FILENAME, 0)

    val IS_LOGGED_IN = "isLoggedIn"
    val AUTH_TOKEN = "authToken"
    val USER_EMAIL = "userEmail"

    //Создаем getter/setter ручками
    var isLoggedIn: Boolean
        //Берем значение из переменной IS_LOGGED_IN
        //по умолчанию утанавливаемое значение - false
        get() = prefs.getBoolean(IS_LOGGED_IN, false)
        //value - значение передаваемое в сеттер
        set(value) = prefs.edit().putBoolean(IS_LOGGED_IN, value).apply()

    var authToken: String
        get() = prefs.getString(AUTH_TOKEN, "")
        set(value) = prefs.edit().putString(AUTH_TOKEN, value).apply()

    var userEmail: String
        get() = prefs.getString(USER_EMAIL, "")
        set(value) = prefs.edit().putString(USER_EMAIL, value).apply()


    val requestQueue = Volley.newRequestQueue(context)
}