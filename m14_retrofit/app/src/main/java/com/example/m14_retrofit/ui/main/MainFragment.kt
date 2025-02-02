package com.example.m14_retrofit.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.example.m14_retrofit.R
import com.example.m14_retrofit.databinding.FragmentMainBinding

import kotlinx.coroutines.flow.collect

import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            viewModel.viewModelScope.launch {
                viewModel.start()
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.state.collect {
                when (it) {
                    State.ColdStart -> {
                        binding.button.isEnabled=true
                        binding.name.text = viewModel.user.value
                        binding.lastName.text = viewModel.userLastName.value
                        binding.progressBar.isVisible = true
                        binding.progressBar.isIndeterminate = true
                    }
                    State.Completed -> {
                        binding.button.isEnabled=true
                        binding.name.text = viewModel.user.value
                        binding.lastName.text = viewModel.userLastName.value
                        binding.progressBar.isVisible = false
                        binding.progressBar.isIndeterminate = false
                        Log.d("TAG", "${viewModel.userCode.value}" + " ${viewModel.user.value}")
                        Glide.with(this@MainFragment)
                            .load(viewModel.userImg.value)
                            .into(binding.imageView)

                    }
                    State.Wait -> {
                        binding.button.isEnabled=false
                        binding.name.text = viewModel.user.value
                        binding.lastName.text = viewModel.userLastName.value
                        Log.d("TAG", "${viewModel.userCode.value}" + " ${viewModel.user.value}")
                        Glide.with(this@MainFragment)
                            .load(viewModel.userImg.value)
                            .into(binding.imageView)
                    }
                    State.Error -> {
                        binding.button.isEnabled=true
                        binding.name.text = "error"
                        binding.lastName.text = "error"
                        binding.imageView.setImageResource(R.drawable.ic_connection_error)
                    }
                }
            }
        }
    }
}