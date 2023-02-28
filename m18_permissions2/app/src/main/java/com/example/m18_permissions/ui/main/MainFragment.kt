package com.example.m18_permissions.ui.main


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf

import androidx.fragment.app.activityViewModels

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.m18_permissions.R
import com.example.m18_permissions.App
import com.example.m18_permissions.database.Photo
import com.example.m18_permissions.databinding.FragmentMainBinding
import kotlinx.coroutines.launch


class MainFragment : Fragment() {


    private var _binding: FragmentMainBinding? = null
    private val binding get()=_binding!!

    private val viewModel: MainViewModel by activityViewModels {
        MainViewModelFactory((activity?.application as App).db.photoDao())
    }


    companion object {
        fun newInstance() = MainFragment()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*lifecycleScope.launchWhenStarted {
            val photo=Photo("11")
          viewModel.insert(photo)

            viewModel.state.collect() {
                it.photo
            }
        }*/
        /*binding.btnAdd.setOnClickListener{
            findNavController().navigate(R.id.action_blankFragment_to_cameraFragment)
        }
*/

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=FragmentMainBinding.inflate(inflater,container,false)

        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_cameraFragment)
        }
        return binding.root
    }


}