package com.example.m11_timer_data_storage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.m11_timer_data_storage.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val rep = Repository()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editText.setText(rep.getText(this))

        binding.buttonSave.setOnClickListener {
            val value=binding.editText.text.toString()
            rep.saveText(value)
            binding.input.setText(rep.getText(this))

        }

        binding.buttonClear.setOnClickListener {
            rep.clearText()
            binding.input.setText(rep.getText(this))
        }

    }
}