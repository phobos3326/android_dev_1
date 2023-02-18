package com.example.m17_recyclerview.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.m17_recyclerview.databinding.FragmentItemBinding


class ItemFragment : Fragment() {

    private var _binding: FragmentItemBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*//val myAdapter = MyAdapter()
        binding.recyclerView.adapter = pageAdapter
        val photo = this@MainFragment.viewModel.marsPhoto
        pageAdapter.setData(photo)*/
        Glide.with(this@ItemFragment)
            .load(arguments?.getString("Arg"))
            .into(binding.itemImageView)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.marsPhoto.collect{


            }
        }
    }
}