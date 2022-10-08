package com.example.m11_timer_data_storage

import android.content.Context
import android.content.SharedPreferences

/*const val APP_PREF = "APP_PREF"
const val KEY_PREF = "KEY_PREF"*/




class Repository {
    val APP_PREF = "APP_PREF"
    val KEY_PREF = "KEY_PREF"


    private lateinit var preferences: SharedPreferences

     fun getDataFromSharedPreference(context: Context): String? {
        val pref = context.getSharedPreferences(APP_PREF, Context.MODE_PRIVATE)
        return pref.getString(KEY_PREF, null)

    }

    private fun getDataFromLocalVariable(): String? {
        return "local val"
    }

    fun saveText(text: String, context: Context) {

      val prf=  context.getSharedPreferences(APP_PREF, Context.MODE_PRIVATE)

            prf.edit()
                .putString(KEY_PREF, text)
                .apply()
    }

    fun clearText() {
    }

    fun getText(context: Context): String? {
        return null
    }
}
