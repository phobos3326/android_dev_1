package com.example.m17_recyclerview.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.m17_recyclerview.R


import com.example.m17_recyclerview.databinding.FragmentMainBinding
import com.example.m17_recyclerview.entity.ModelPhotos
import kotlinx.coroutines.launch


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()

    private val pageAdapter = MyAdapter { onItemClick(it) }



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
            //val myAdapter = MyAdapter()
            binding.recyclerView.adapter = pageAdapter
            val photo = this@MainFragment.viewModel.loadPhotos()
            pageAdapter.setData(photo)
        }


    }




    private fun onItemClick(item: ModelPhotos.Photo) {

        findNavController().navigate(R.id.ItemFragment)
    }
}