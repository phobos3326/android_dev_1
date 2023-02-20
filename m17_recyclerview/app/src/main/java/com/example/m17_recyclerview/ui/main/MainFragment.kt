package com.example.m17_recyclerview.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.m17_recyclerview.R


import com.example.m17_recyclerview.databinding.FragmentMainBinding
import com.example.m17_recyclerview.di.DaggerAppComponent
import com.example.m17_recyclerview.entity.ModelPhotos


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels{
        DaggerAppComponent.create().mainViwModelFactory()
    }

    private val pageAdapter = MyAdapter { onItemClick(it) }
    val bundle = Bundle()
    companion object {
        private const val USER_ID_KEY = "userIdKey"
        fun newInstance() = MainFragment()
        fun createArguments(userIdKey: String): Bundle {
            return bundleOf(USER_ID_KEY to userIdKey)
        }
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

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.marsPhoto.collect {
                binding.recyclerView.adapter = pageAdapter
                pageAdapter.setData(it)
            }
        }
    }

    private fun onItemClick(item: ModelPhotos.Photo) {
        bundle.putString("Arg", item.imgSrc)
        findNavController().navigate(R.id.action_mainFragment_to_itemFragment, bundle)

    }
}