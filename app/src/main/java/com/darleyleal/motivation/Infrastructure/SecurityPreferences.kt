package com.darleyleal.motivation.Infrastructure

import android.content.Context
import android.content.SharedPreferences

class SecurityPreferences(context: Context) {
    private val security: SharedPreferences =
        context.getSharedPreferences("", Context.MODE_PRIVATE)

    fun putValue(key: String, value: String) {
        security.edit().putString(key, value).apply()
    }

    fun getValue(key: String): String {
        return security.getString(key, "") ?: ""
    }
}