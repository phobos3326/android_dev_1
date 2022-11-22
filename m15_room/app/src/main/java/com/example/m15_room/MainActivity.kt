package com.example.m15_room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.m15_room.databinding.ActivityMainBinding
import com.example.m15_room.ui.main.App
import com.example.m15_room.ui.main.MainViewModel
import com.example.m15_room.ui.main.database.State


class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val wordDao = (application as App).db.wordDao()
                return MainViewModel(wordDao = wordDao) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editText.addTextChangedListener {
            lifecycleScope.launchWhenCreated {
                viewModel.insertWord = it.toString()
                viewModel.getWordMatches()?.observe(this@MainActivity) {
                    binding.textView.text = it.joinToString(separator = "\r\n")
                }
            }
        }


        binding.button.setOnClickListener { viewModel.onAddBtn() }
        binding.buttonDell.setOnClickListener { viewModel.onDeleteButton() }


        lifecycleScope.launchWhenStarted {
            viewModel.state.collect {
                when (it) {
                    State.Start -> {
                        viewModel.getAllWords().observe(this@MainActivity) {
                            binding.textView.text = it.joinToString(separator = "\r\n")
                            Log.d("state", "Star")

                        }
                    }
                    State.Clear -> {
                        viewModel.getAllWords().observe(this@MainActivity) {
                            binding.textView.text = it.joinToString(separator = "\r\n")
                            Log.d("state", "Clear")
                        }
                    }
                    State.Matches -> {
                        viewModel.getWordMatches()?.observe(this@MainActivity) {
                            binding.textView.text = it.joinToString(separator = "\r\n")
                            Log.d("state", "Matches")
                        }
                    }
                }
            }
        }

        /*  fun getItemsFromDb(searchText: String) {
             var searchText = searchText
             searchText = "%$searchText%"

             viewModel.getGetWordMatches()?.observe(this@MainActivity) { list ->
                 list?.let {
                     Log.e("List = ", list.toString())
                 }

             }

          }*/

        /*  lifecycleScope.launchWhenCreated {
              viewModel.getGetWordMatches().collect {

                  Log.d("TAG", it.toString())
              }
          }*/

    }
}