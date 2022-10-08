package com.example.m11_timer_data_storage

import android.content.Context
import android.content.SharedPreferences


const val APP_PREF = "APP_PREF"
const val KEY_PREF = "KEY_PREF"


class Repository {
    private var localVal:String?=null

    private lateinit var preferences: SharedPreferences

    private fun getDataFromSharedPreference(context: Context): String? {
        preferences = context.getSharedPreferences(APP_PREF, Context.MODE_PRIVATE)
        return preferences.getString(KEY_PREF, null)

    }

    private fun getDataFromLocalVariable(): String? {
        return localVal
    }

    fun saveText(text: String) {

        preferences.edit()
            .putString(KEY_PREF, text)
            .apply()

        localVal = preferences.getString(KEY_PREF, null).toString()

    }

    fun clearText() {
        preferences.edit()
            .clear()
            .apply()
    }

    fun getText(context: Context): String? {

        return when {
            getDataFromLocalVariable() != null -> getDataFromLocalVariable()
            getDataFromSharedPreference(context) != null -> getDataFromSharedPreference(context)

            else -> context.getString(R.string.`val`)
        }
    }
}
