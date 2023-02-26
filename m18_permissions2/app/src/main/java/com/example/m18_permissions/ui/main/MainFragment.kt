package com.example.m18_permissions.ui.main


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.activityViewModels

import androidx.lifecycle.lifecycleScope
import com.example.m18_permissions.R
import com.example.m18_permissions.App
import com.example.m18_permissions.database.Photo
import com.example.m18_permissions.databinding.FragmentMainBinding
import kotlinx.coroutines.launch


class MainFragment : Fragment() {


    private var _binding: FragmentMainBinding? = null
    private var binding = _binding

    private val viewModel: MainViewModel by activityViewModels {
        MainViewModelFactory((activity?.application as App).db.photoDao())
    }


    companion object {
        fun newInstance() = MainFragment()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenStarted {
            val photo=Photo("pdifmopfm")
          viewModel.insert(photo)

            viewModel.state.collect() {
                it.photo
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }
}