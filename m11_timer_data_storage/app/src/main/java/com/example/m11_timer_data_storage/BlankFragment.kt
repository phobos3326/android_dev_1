package com.example.m11_timer_data_storage

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.m11_timer_data_storage.databinding.FragmentBlankBinding

class BlankFragment : Fragment() {

    var rep = Repository()
    private var _binding: FragmentBlankBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentBlankBinding.inflate(inflater, container, false)

        val appContext = context?.applicationContext
        binding.editText.setText(rep.getText(appContext))

        binding.buttonSave.setOnClickListener {
            val value = binding.editText.text.toString()
            rep.saveText(value)
            binding.textField.editText?.setText(rep.getText(appContext))

        }

        binding.buttonClear.setOnClickListener {
            rep.clearText()
            binding.textField.editText?.setText(rep.getText(appContext))
        }
        return binding.root
    }
}