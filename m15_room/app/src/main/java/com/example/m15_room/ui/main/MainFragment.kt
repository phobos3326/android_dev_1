package com.example.m15_room.ui.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.m15_room.databinding.FragmentMainBinding


class MainFragment : Fragment() {

   val appContext= App().getInstance()




    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels {
        object :ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val wordDao=(appContext as App).db.wordDao()
                return MainViewModel(wordDao) as T
            }
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }

    // private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)

     /*   lifecycleScope.launchWhenCreated {
            viewModel.words.collect {
                binding.textView.text=it.joinToString(separator = "\r\n")
            }

        }*/

        //val wordDao=(appContext as App).db.wordDao()

        //binding.textView.text = wordDao.getAll().toString()

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        //binding.button.setOnClickListener { viewModel.onAddBtn() }



    }
}