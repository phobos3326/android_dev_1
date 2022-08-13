package com.example.m3_components

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.m3_components.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext

import kotlin.coroutines.suspendCoroutine


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        var x = 0

        val job = SupervisorJob()
        val scope = CoroutineScope(Dispatchers.Main + job)

        binding.seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                binding.progressBar.progress = seekBar.progress
                binding.progressTextView.text = seekBar.progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                binding.progressBar.progress = seekBar.progress
                binding.progressTextView.text = seekBar.progress.toString()

                Snackbar.make(view, "Progress is: " + seekBar.progress + "%", Snackbar.LENGTH_LONG)
                    .show()
            }
        })

/*
        when (x) {
            0 -> {
                binding.button.setOnClickListener {
                    //binding.button.isEnabled = false
                    binding.seekBar.isEnabled = false
                    binding.button.text = "stop"
                    *//* binding.button.text = "stop"
                     if ( binding.button.text == "stop"){
                         scope.coroutineContext.job.cancel()
                     }*//*
                    scope.launch {
                        countDown(binding)
                    }
                    x = 1
                }
            }
            1 -> {
                binding.button.setOnClickListener {
                    binding.button.text = "start"
                    binding.seekBar.isEnabled = true

                   scope.cancel()
                }
            }
        }
        */




        binding.button.setOnClickListener {

            when (x) {
                0 -> {
                    x = 1
                    binding.seekBar.isEnabled = false
                    binding.button.text = "stop"
                   val job1=scope.launch(start = CoroutineStart.DEFAULT) {
                        countDown(binding)
                    }


                }
                1 -> {
                    x = 0
                    binding.button.text = "start"
                    binding.seekBar.isEnabled = true
                 //   job1.cancel()
                }
            }
        }


    }

   /* fun startTimer():Job{
        return countDown(binding=ActivityMainBinding.inflate())
    }*/

    suspend fun countDown(binding: ActivityMainBinding) {

        repeat(binding.progressBar.progress) {
            delay(1000)
            binding.progressBar.progress -= 1
            binding.progressTextView.text = binding.progressBar.progress.toString()

        }

        if (binding.progressBar.progress == 0) {
            binding.button.text = "start"
            binding.seekBar.isEnabled = true
            binding.seekBar.progress = 0
            coroutineContext.job.cancel()

        }
    }


}