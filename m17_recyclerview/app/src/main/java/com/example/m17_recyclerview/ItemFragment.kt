package com.example.m17_recyclerview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.m17_recyclerview.databinding.FragmentItemBinding
import com.example.m17_recyclerview.ui.main.MainViewModel


class ItemFragment : Fragment() {

    private var _binding: FragmentItemBinding? = null
    private val binding get() =_binding!!
    private val viewModel:MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item, container, false)
    }

    companion object {


    }
}