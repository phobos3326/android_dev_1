package com.example.m10_timer_life_cycle

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import com.example.m10_timer_life_cycle.databinding.ActivityMainBinding


import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*

var cnt = 0
var btnStart = true
var jobActive = false

const val KEY_CNT = "cnt"
const val KEY_BTN = "btn"
const val KEY_JOB = "job"

var flag = true

class MainActivity : AppCompatActivity() {
    private val scope = CoroutineScope(Dispatchers.Main + Job())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (savedInstanceState != null) {
            cnt = savedInstanceState.getInt(KEY_CNT)
            binding.progressBar.progress = cnt
            binding.progressTextView.text = cnt.toString()
            binding.seekBar.isEnabled = flag
            if (jobActive) {
                countDown(binding).job.start()
                binding.button.text=getString(R.string.button_text_stop)
            }else{
                binding.button.text=getString(R.string.button_text_start)
            }

        }


        binding.seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                cnt = seekBar.progress
                binding.progressBar.progress = cnt
                binding.progressTextView.text = cnt.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                binding.progressBar.progress = seekBar.progress
                binding.progressTextView.text = seekBar.progress.toString()

                Snackbar.make(
                    view,
                    getString(R.string.snackbar_info) + seekBar.progress + "%",
                    Snackbar.LENGTH_LONG
                )
                    .show()
            }
        })


        binding.button.setOnClickListener {
            scope.launch {
                when (flag) {
                    true -> {
                        flag = false
                        jobActive = true
                        binding.seekBar.isEnabled = flag
                        binding.button.text = getString(R.string.button_text_stop)
                        countDown(binding).job.start()
                    }
                    false -> {
                        flag = true
                        jobActive = false
                        binding.seekBar.isEnabled = flag
                        binding.button.text = getString(R.string.button_text_start)
                        scope.coroutineContext.cancelChildren()
                    }
                }
            }
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_CNT, cnt)
        outState.putBoolean(KEY_BTN, flag)
        outState.putBoolean(KEY_JOB, jobActive)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        cnt = savedInstanceState.getInt(KEY_CNT)
        flag = savedInstanceState.getBoolean(KEY_BTN)
        jobActive = savedInstanceState.getBoolean(KEY_JOB)
    }


    private fun countDown(binding: ActivityMainBinding): Job {
        val start = System.currentTimeMillis()
        val job = scope.launch {
            yield()
            repeat(binding.progressBar.progress) {
                delay(1000)
                cnt -= 1
                binding.progressBar.progress = cnt
                binding.progressTextView.text = cnt.toString()
                Log.d("MY_TAG", "$cnt")

                Log.d(
                    "MY_TAG",
                    "(on ${Thread.currentThread().name}) " +
                            "after ${(System.currentTimeMillis() - start) / 1000}s"
                )
                if (cnt <= 0) {
                    scope.coroutineContext.job.cancel()
                    binding.button.text = getString(R.string.button_text_start)
                    binding.seekBar.isEnabled = true
                    binding.seekBar.progress = 0


                }
            }
        }
        return job
    }

    override fun onDestroy() {

        scope.coroutineContext.job.cancel()


        super.onDestroy()
    }

}