package com.stanislavkorneev.korneevapp

import android.content.Context
import android.content.SharedPreferences

class Preferences(context: Context) {

    private val preferences: SharedPreferences = context.getSharedPreferences("APP", Context.MODE_PRIVATE)

    var tokenPreferences: String?
        get() = preferences.getString("TOKEN", null)
        set(value) = preferences.edit().putString("TOKEN", value).apply()

    var loanIdPreferences: Int
        get() = preferences.getInt("ID", 0)
        set(value) = preferences.edit().putInt("ID", value).apply()
}
