package com.example.m12_mvvm

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.m12_mvvm.databinding.FragmentBlankBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class MainFragment : Fragment() {

    private var _binding: FragmentBlankBinding? = null
    private val binding get() = _binding!!


    private val viewModel: ViewModel by viewModels()

    //    val viewModel = ViewModelProvider(this).get(ViewModel::class.java)
    private val scope = CoroutineScope(Dispatchers.Main + Job())


    companion object {
        fun newInstance() = MainFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBlankBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.editText1.addTextChangedListener { str ->
            var findStr = str.toString()
            val a = viewModel.strLength(findStr)

        }
        binding.butTon.setOnClickListener {
            viewModel.viewModelScope.launch {
                viewModel.onClick()

            }

        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.state.collect {
                when (it) {
                    State.Blank -> {
                        binding.progressBar.isVisible = false
                        binding.butTon.isEnabled = false
                        Snackbar.make(binding.root, "blank", Snackbar.LENGTH_SHORT).show()
                        Log.d("state", "blank")
                    }
                    State.Ready -> {
                        binding.progressBar.isVisible = false
                        binding.butTon.isEnabled = true
                        binding.textView.text = viewModel.requestResult
                        Log.d("state", "ready")
                        Snackbar.make(binding.root, "Ready", Snackbar.LENGTH_SHORT).show()
                    }
                    State.Find -> {
                        binding.progressBar.isVisible = true
                        binding.progressBar.isIndeterminate = true
                        binding.butTon.isEnabled = false
                        Snackbar.make(binding.root, "find", Snackbar.LENGTH_SHORT).show()
                        Log.d("state", "find")
                    }
                    State.Completed -> {
                        binding.progressBar.isVisible = false
                        binding.butTon.isEnabled = true

                        binding.textView.text = viewModel.requestResult()
                        Snackbar.make(binding.root, "completed", Snackbar.LENGTH_SHORT).show()
                        Log.d("state", "completed")
                    }


                }
            }
        }


    }


}