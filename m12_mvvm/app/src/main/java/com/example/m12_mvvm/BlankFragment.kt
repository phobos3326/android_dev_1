package com.example.m12_mvvm

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.example.m12_mvvm.databinding.FragmentBlankBinding
import com.google.android.material.snackbar.Snackbar

class BlankFragment : Fragment() {

    private var _binding: FragmentBlankBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = BlankFragment()
    }

    private lateinit var viewModel: BlankViewModel

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

        var a: String =""
        binding.editText1.addTextChangedListener {
            binding.butTon.isEnabled = it?.length!! > 3
             a = binding.editText1.text.toString()
        }

        binding.butTon.setOnClickListener {
            Snackbar.make(binding.root, a, Snackbar.LENGTH_SHORT).show()
        }

    }

}