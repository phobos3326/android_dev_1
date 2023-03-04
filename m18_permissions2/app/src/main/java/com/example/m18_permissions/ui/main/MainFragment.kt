package com.example.m18_permissions.ui.main


import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.viewModels

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.m18_permissions.R
import com.example.m18_permissions.adapter.Adapter
import com.example.m18_permissions.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {


    private val viewModel: MainViewModel by viewModels()

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val pageAdapter = Adapter{}

    /* private val viewModel: MainViewModel by activityViewModels {
         MainViewModelFactory((activity?.application as App).db.photoDao())
     }*/


    companion object {
        fun newInstance() = MainFragment()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*binding.btnAdd.setOnClickListener{
            findNavController().navigate(R.id.action_blankFragment_to_cameraFragment)
        }
*/

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)


        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.state.collect {
                binding.recyclerView.adapter =pageAdapter
                it.photo?.let { it1 -> pageAdapter.setData(it1) }
            }

        }

        lifecycleScope.launchWhenStarted {

            Glide.with(this@MainFragment)
                .load(Uri.parse(viewModel.takeOne()))
                .centerInside()
                .circleCrop()

                .into(binding.imageView2)

            viewModel.state.collect {


            }


        }

        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_cameraFragment)
        }
        return binding.root
    }


}