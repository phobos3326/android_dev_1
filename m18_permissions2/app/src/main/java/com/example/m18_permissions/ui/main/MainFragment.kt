package com.example.m18_permissions.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.example.m18_permissions.R
import com.example.m18_permissions.App
import com.example.m18_permissions.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    private val application = App()

    //val photoDao = (application as App).db.photoDao()
    private var _binding: FragmentMainBinding? = null
    private var binding = _binding

      /*private val viewModel: MainViewModel by viewModels {
          MainViewModelFactory((application as App).db.photoDao())
      }*/



    companion object {
        fun newInstance() = MainFragment()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
          /*  lifecycleScope.launchWhenStarted {
                viewModel.state.collect(){
                    it.photo
                }
            }*/


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        return inflater.inflate(R.layout.fragment_main, container, false)

    }

}