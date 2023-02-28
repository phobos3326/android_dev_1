package com.example.m18_permissions.ui.main

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

import android.Manifest
import android.content.Context
import com.example.m18_permissions.R
import com.example.m18_permissions.databinding.FragmentCameraBinding
import com.example.m18_permissions.databinding.FragmentMainBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CameraFragment : Fragment() {

    //var context = requireContext()

    private var _binding: FragmentCameraBinding? = null
    private val binding get() = _binding!!

    private val launch =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            Toast.makeText(this.requireContext(), "permission is $isGranted", Toast.LENGTH_SHORT)
                .show()
        }

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

        }
    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA,
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(context, "granted", Toast.LENGTH_SHORT).show()
        } else {
            launch.launch(Manifest.permission.CAMERA)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentCameraBinding.inflate(inflater,container,false)
        checkPermission()
        return binding.root
    }

     companion object {

         @JvmStatic
         fun newInstance(param1: String, param2: String) =
             CameraFragment().apply {
                 arguments = Bundle().apply {
                     putString(ARG_PARAM1, param1)
                     putString(ARG_PARAM2, param2)
                 }
             }
     }
}