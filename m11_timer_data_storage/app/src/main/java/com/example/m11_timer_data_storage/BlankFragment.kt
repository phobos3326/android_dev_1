package com.example.m11_timer_data_storage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.m11_timer_data_storage.databinding.FragmentBlankBinding


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class BlankFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null


    private var _binding: FragmentBlankBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        var rep =Repository()


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding=FragmentBlankBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BlankFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}