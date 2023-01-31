package com.example.m16_architecture


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope

import com.example.m16_architecture.databinding.FragmentMainBinding
import com.example.m16_architecture.di.DaggerAppComponent
import com.example.m16_architecture.presentation.MainViewModel
import kotlinx.coroutines.flow.collect

import kotlinx.coroutines.launch



class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    val viewModel: MainViewModel by viewModels {
        DaggerAppComponent.create().mainViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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


        lifecycleScope.launchWhenStarted {
            viewModel.state.collect{
                binding.textView.text=it.activity
            }
        }

    }



}