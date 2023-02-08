package com.example.m17_recyclerview.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.example.m17_recyclerview.R
import com.example.m17_recyclerview.data.ModelPhotoRepository
import com.example.m17_recyclerview.databinding.FragmentMainBinding
import com.example.m17_recyclerview.domain.GetPhotoUseCase
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    private var _binding:FragmentMainBinding? =null
    private val binding get() = _binding!!

    private val viewModel:MainViewModel by viewModels()

    val repository =ModelPhotoRepository()
    companion object {
        fun newInstance() = MainFragment()
    }

   // private lateinit var viewModel: MainViewModel

 /*   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

    }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewModelScope.launch {
            //binding.message.text=GetPhotoUseCase().execute().photos.toString()

        }
    }
}