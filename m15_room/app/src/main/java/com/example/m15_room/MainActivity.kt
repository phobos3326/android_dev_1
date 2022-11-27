package com.example.m15_room


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.util.Log

import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.*
import com.example.m15_room.databinding.ActivityMainBinding
import com.example.m15_room.ui.main.App
import com.example.m15_room.ui.main.MainViewModel
import com.example.m15_room.ui.main.State


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


        binding.editText.addTextChangedListener {

            viewModel.insertWord = it.toString()
            viewModel.viewModelScope.launch {

                viewModel.getWordMatches()?.collect {
                    binding.textView.text = it.joinToString(separator = "\r\n")
                }
            }
        }



        binding.button.setOnClickListener { viewModel.onAddBtn() }
        binding.buttonDell.setOnClickListener { viewModel.onDeleteButton() }


        binding.buttonCheck.setOnClickListener {
            viewModel.validatePassword()

        }


        /*lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.state.observe(this@MainActivity ) {
                    when (it) {
                        State.Start -> {
                            launch {
                                viewModel.allWords.collect{
                                    binding.textView.text = it.joinToString(separator = "\r\n")
                                    Log.d("state", "Start")
                                    Snackbar.make(binding.root, "Start", Snackbar.LENGTH_SHORT).show()
                                }
                            }

                        }
                        State.Clear -> {
                            launch {
                                viewModel.allWords.collect {
                                    binding.textView.text = it.joinToString(separator = "\r\n")
                                    Log.d("state", "Clear")
                                    Snackbar.make(binding.root, "clear", Snackbar.LENGTH_SHORT).show()
                                }
                            }

                        }
                        State.Matches -> {
                            launch {
                                viewModel.getWordMatches()?.collect {
                                    binding.textView.text = it.joinToString(separator = "\r\n")
                                    Log.d("state", "Matches")
                                    Snackbar.make(binding.root, "Matches", Snackbar.LENGTH_SHORT).show()
                                }
                            }

                        }
                    }
                }
            }
        }
*/

       lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state ->
                when (state) {
                    State.Start -> {
                        viewModel.allWords.collect {
                            binding.textView.text = it.joinToString(separator = "\r\n")
                            Log.d("state", "Start")
                        }
                    }
                    State.Clear -> {
                        viewModel.allWords.collect {
                            binding.textView.text = it.joinToString(separator = "\r\n")
                            Log.d("state", "Clear")
                        }
                    }
                    State.Matches -> {
                        viewModel.getWordMatches()?.collect {
                            binding.textView.text = it.joinToString(separator = "\r\n")
                            Log.d("state", "Matches")
                        }
                    }
                    State.ErrorInput -> {
                        binding.textInputLayout.error = "Field can not be empty"
                        Log.d("state", "ErrInput")
                    }
                    State.Validate -> {
                        binding.textInputLayout.error = "Password is too weak"
                        Log.d("state", "Password is too weak")
                    }
                    State.WhiteSpaces -> {

                    }
                }
            }
        }


    }


}