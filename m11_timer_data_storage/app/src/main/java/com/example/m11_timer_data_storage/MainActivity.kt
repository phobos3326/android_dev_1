package com.example.m11_timer_data_storage

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.m11_timer_data_storage.databinding.ActivityMainBinding




class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //private lateinit var preferences: SharedPreferences
    val rep = Repository()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editText.setText(rep.getDataFromSharedPreference(this))

      /*  preferences=getSharedPreferences(APP_PREF, MODE_PRIVATE)

        binding.editText.setText(preferences.getString(KEY_PREF, ""))*/

        binding.buttonSave.setOnClickListener {
            val value=binding.editText.text.toString()
            rep.saveText(value,this)
           /* preferences.edit()
                .putString(KEY_PREF,value)
                .apply()*/
        }

    }
}