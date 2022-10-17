package com.example.m12_mvvm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.example.m12_mvvm.databinding.FragmentBlankBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*

class MainFragment : Fragment() {

    private var _binding: FragmentBlankBinding? = null
    private val binding get() = _binding!!

    private val scope = CoroutineScope(Dispatchers.Main + Job())

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBlankBinding.inflate(inflater, container, false)
        return binding.root
    }





    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // viewModel = ViewModelProvider(this).get(BlankViewModel::class.java)
        // TODO: Use the ViewModel

        var a: String = ""
        binding.editText1.addTextChangedListener {
            binding.butTon.isEnabled = it?.length!! > 3
            a = binding.editText1.text.toString()
        }

        binding.butTon.setOnClickListener {
            Snackbar.make(binding.root, a, Snackbar.LENGTH_SHORT).show()
            scope.launch {
                // delay(5000)


                val bb = download(binding)
                Snackbar.make(binding.root, bb, Snackbar.LENGTH_SHORT).show()
            }


        }

    }


    private suspend fun download(binding: FragmentBlankBinding): String {

            binding.progressBar.isIndeterminate = true
            binding.progressBar.isVisible=true
            delay(5_000L)
            binding.progressBar.isIndeterminate = false
            binding.progressBar.isVisible=false

        return "completed"
    }

}