package com.example.m14_retrofit.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.example.m14_retrofit.databinding.FragmentMainBinding

import kotlinx.coroutines.flow.collect

import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()

    }

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
                        userData()
                    }
                    State.Completed -> {
                        userData()
                    }
                    State.Wait->{
                        userData()
                    }

                }

            }
        }


    }

    private fun userData() {
        binding.name.text = viewModel.user.value
        binding.lastName.text = viewModel.userLastName.value
        Log.d("TAG", "${viewModel.userCode.value}" + " ${viewModel.user.value}")
        Glide.with(this@MainFragment)
            .load(viewModel.userImg.value)
            .into(binding.imageView)
    }


}