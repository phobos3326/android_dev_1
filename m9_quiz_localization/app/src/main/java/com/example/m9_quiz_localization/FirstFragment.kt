package com.example.m9_quiz_localization

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.m9_quiz_localization.R
import com.example.m9_quiz_localization.databinding.FragmentFirstBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Calendar
import kotlin.concurrent.fixedRateTimer


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private val calendar =Calendar.getInstance()
    private val dateFormat=SimpleDateFormat("dd_MM_yyyy")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentFirstBinding.inflate(inflater, container, false)



        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    activity?.finish()
                }
            }
        )

        binding.buttonStart.setOnClickListener {

            val builder: NavOptions.Builder = NavOptions.Builder()
            val navOptions = builder.setEnterAnim(R.animator.alpha).build()
            findNavController().navigate(
                R.id.action_firstFragment_to_secondFragment,
                null,
                navOptions
            )
        }

        binding.buttonAge.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Set date")
                .build()
            //fragmentManager?.let { it1 -> datePicker.show(it1,"dofnb") }
            datePicker.addOnPositiveButtonClickListener { timeInMillis ->
                calendar.timeInMillis=timeInMillis
                Snackbar.make(binding.buttonAge, dateFormat.format(calendar.time),Snackbar.LENGTH_SHORT).show()
            }
            datePicker.show(childFragmentManager, "dkjfvn")


        }

        return return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FirstFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FirstFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }


}