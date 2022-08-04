package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var count = 0
    val maxCount = 5
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.centerText.text = getString(R.string.all_places_free)
        binding.leftButton.isEnabled = false

        binding.rightButton.setOnClickListener {
            increment(binding)
        }

        binding.leftButton.setOnClickListener {
            decrement(binding)
        }

        binding.centerButton.setOnClickListener {
            reset(binding)
        }
    }

    private fun reset(binding: ActivityMainBinding) {
        count = 0
        binding.rightButton.isEnabled = true
        binding.leftButton.isEnabled = false
        binding.centerBottomText.text = count.toString()
        binding.centerText.text = getString(R.string.all_places_free)
        binding.centerButton.visibility = View.INVISIBLE
    }

    private fun increment(binding: ActivityMainBinding) {
        count++
        binding.centerBottomText.text = count.toString()
        binding.centerText.text = buildString {
            append(getString(R.string.seats_left))
            append(maxCount - count)
        }
        if (count == maxCount) {
            binding.centerButton.visibility = View.VISIBLE
            binding.rightButton.isEnabled = false
        }
        if (count > 0) binding.leftButton.isEnabled = true

    }

    private fun decrement(binding: ActivityMainBinding) {
        count -= 1
        binding.centerBottomText.text = count.toString()
        binding.centerText.text = buildString {
            append(getString(R.string.seats_left))
            append(maxCount - count)
        }
        if (count != 0) binding.rightButton.isEnabled = true
        if (count == 0) binding.leftButton.isEnabled = false
        if (count < maxCount) binding.centerButton.visibility = View.INVISIBLE
    }

}