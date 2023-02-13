package com.example.m17_recyclerview.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.m17_recyclerview.R
import com.example.m17_recyclerview.data.ModelPhotoRepository
import com.example.m17_recyclerview.databinding.FragmentMainBinding
import com.example.m17_recyclerview.domain.GetPhotoUseCase
import com.example.m17_recyclerview.entity.ModelPhotos
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()





    companion object {
        fun newInstance() = MainFragment()
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewModelScope.launch {
            //binding.message.text=GetPhotoUseCase().execute().photos.toString()
            binding.recyclerView.layoutManager=GridLayoutManager(requireContext(),2)
            val photo = viewModel.loadPhotos()
            val myAdapter = MyAdapter(photo)
            binding.recyclerView.adapter=myAdapter
        }
    }
}