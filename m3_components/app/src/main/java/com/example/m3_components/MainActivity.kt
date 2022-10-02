package com.example.m3_components

import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import com.example.m3_components.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*

var cnt = 0

const val KEY = "cnt"

class MainActivity : AppCompatActivity() {
    private val scope = CoroutineScope(Dispatchers.Main + Job())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        var x = 0

        if (savedInstanceState != null) {
            cnt = savedInstanceState.getInt(KEY)

            countDown(binding).job.start()
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


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY, cnt)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        cnt = savedInstanceState.getInt(KEY)

    }


    fun countDown(binding: ActivityMainBinding): Job {
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
                            "after ${(System.currentTimeMillis() - start) / 1000F}s"
                )
                if (cnt <= 0) {
                    binding.button.text = getString(R.string.button_text_start)
                    binding.seekBar.isEnabled = true
                    binding.seekBar.progress = 0
                    cancel()
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