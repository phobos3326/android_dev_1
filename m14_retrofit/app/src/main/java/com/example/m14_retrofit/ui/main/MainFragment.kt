package com.example.m14_retrofit.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.m14_retrofit.databinding.FragmentMainBinding
import com.example.m14_retrofit.ui.main.network.RetrofitInstance
import com.example.m14_retrofit.ui.main.network.data.Test
import com.example.m14_retrofit.ui.main.network.data.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()

    }


    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    //  private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //  viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
        start()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMainBinding.inflate(inflater, container, false)

        /*    viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                binding.message.text = viewModel.str
            }*/






        return binding.root

    }

    private fun start() {
        RetrofitInstance.searchUserApi.getUser().enqueue(object : Callback<Test> {
            override fun onResponse(call: Call<Test>, response: Response<Test>) {
                val user = response.body() ?: return
                val status = response.code()
                //binding.message.text = user.toString()
                Log.d("TAG", "$user")
                user.results.forEach {
                    binding.gender.text = it.gender
                    binding.name.text = buildString {
                        append(it.name.first)
                        append(it.name.last)
                        append(it.name.title)
                    }
                }
            }


            override fun onFailure(call: Call<Test>, t: Throwable) {

            }

        })
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


}