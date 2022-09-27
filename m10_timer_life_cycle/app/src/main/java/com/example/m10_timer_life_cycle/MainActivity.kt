package com.example.m10_timer_life_cycle

import android.os.Bundle
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import com.example.m3_components.R
import com.example.m3_components.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    val scope = CoroutineScope(Dispatchers.Main + Job())

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

                Snackbar.make(view, getString(R.string.snackbar_info) + seekBar.progress + "%", Snackbar.LENGTH_LONG)
                    .show()
            }
        })


        binding.button.setOnClickListener {
            scope.launch {
                when (x) {
                    0 -> {
                        x = 1
                        binding.seekBar.isEnabled = false
                        binding.button.text = getString(R.string.button_text_stop)
                        countDown(binding).job.start()
                    }
                    1 -> {
                        x = 0
                        binding.button.text = getString(R.string.button_text_start)
                        binding.seekBar.isEnabled = true
                        scope.coroutineContext.cancelChildren()
                    }
                }
            }
        }
    }


    fun countDown(binding: ActivityMainBinding): Job {
       val job = scope.launch {
            yield()
            repeat(binding.progressBar.progress) {
                delay(1000)
                binding.progressBar.progress -= 1
                binding.progressTextView.text = binding.progressBar.progress.toString()

            }
            if (binding.progressBar.progress == 0) {
                binding.button.text = getString(R.string.button_text_start)
                binding.seekBar.isEnabled = true
                binding.seekBar.progress = 0
                cancel()
            }
        }
        return job
    }
}