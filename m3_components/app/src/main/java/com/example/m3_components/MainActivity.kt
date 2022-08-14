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
    val job = SupervisorJob()
    val scope = CoroutineScope(Dispatchers.Main + job)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        var x = 0



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


        binding.button.setOnClickListener {
            scope.launch {

                when (x) {
                    0 -> {
                        x = 1
                        binding.seekBar.isEnabled = false
                        binding.button.text = "stop"

                        countDown(binding).job.start()


                    }
                    1 -> {
                        x = 0
                        binding.button.text = "start"
                        binding.seekBar.isEnabled = true
                        countDown(binding).job.cancelAndJoin()
                        //  job1.cancel()
                    }
                }
            }

        }


    }
    lateinit var job1: Job

    fun countDown(binding: ActivityMainBinding): Job {
         job1 = scope.launch {
            repeat(binding.progressBar.progress) {
                delay(1000)
                binding.progressBar.progress -= 1
                binding.progressTextView.text = binding.progressBar.progress.toString()

            }

            if (binding.progressBar.progress == 0) {
                binding.button.text = "start"
                binding.seekBar.isEnabled = true
                binding.seekBar.progress = 0
                job1.cancel()

            }
        }
        return job1
    }


}