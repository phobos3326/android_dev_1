package com.example.m15_room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.m15_room.databinding.ActivityMainBinding
import com.example.m15_room.ui.main.App
import com.example.m15_room.ui.main.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val viewModel: MainViewModel by viewModels {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    val wordDao = (application as App).db.wordDao()
                    return MainViewModel(wordDao) as T
                }
            }
        }


        lifecycleScope.launchWhenCreated {
            viewModel.words.collect {
                binding.textView.text = it.joinToString(separator = "\r\n")
            }

        }

        binding.button.setOnClickListener {
            binding.textView.text= viewModel.onAdd().toString()
        }

        binding.buttonDell.setOnClickListener {
            viewModel.onDelete()
        }


    }
}