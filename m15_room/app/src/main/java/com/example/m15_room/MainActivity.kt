package com.example.m15_room


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher

import android.util.Log

import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope


import com.example.m15_room.databinding.ActivityMainBinding
import com.example.m15_room.ui.main.App
import com.example.m15_room.ui.main.MainViewModel
import com.example.m15_room.ui.main.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.stateIn


import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    private val viewModel: MainViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val wordDao = (application as App).db.wordDao()
                return MainViewModel(wordDao = wordDao, App()) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textInputLayout.editText?.addTextChangedListener {
            viewModel.insertWord = it.toString()
            viewModel.validatePassword()
        }

        binding.button.setOnClickListener {
            viewModel.onAddBtn()
        }
        binding.buttonDell.setOnClickListener {
            viewModel.onDeleteButton()
        }



        binding.buttonCheck.setOnClickListener {
            viewModel.validatePassword()
        }

        lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state ->
                when(state) {
                    State.Clear -> TODO()
                    is State.Content -> {

                       binding.textView.text = state.words.joinToString( separator = "\r\n" )
                       binding.textInputLayout.error=state.input


                    }
                   is State.ErrorInput -> {

                   }
                    State.Matches -> TODO()
                    State.Start -> {}
                    State.Validate -> TODO()
                    State.WhiteSpaces -> TODO()
                }
            }
        }




        /*lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state ->
                when (state) {
                    is State.Start -> {
                        state.allWords.joinToString ()
                        viewModel.allWords
                        binding.textView.text = it.joinToString(separator = "\r\n")
                         Log.d("state", "start2")

                    }
                    State.Clear -> {
                         viewModel.allWords.observe(this@MainActivity) {
                             binding.textView.text = it.joinToString(separator = "\r\n")
                             Log.d("state", "Clear")
                         }
                    }
                    State.ErrorInput -> {
                        binding.textInputLayout.error = "Field can not be empty"
                        // Log.d("state", "ErrInput")
                    }
                    State.Validate -> {
                        binding.textInputLayout.error = "Password is too weak"
                        //Log.d("state", "Password is too weak")
                    }
                    State.WhiteSpaces -> {

                    }
                    is State.Matches -> {
                        binding.textView.text = state.listMatches?.joinToString(separator = "\r\n")
                         state.listMatches?.observe(this@MainActivity) {
                             binding.textView.text = it.joinToString(separator = "\r\n")
                             // Log.d("state", "match4")
                         }
                    }
                }
            }
        }*/
    }
}