package com.example.m7_quiz_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.m7_quiz_fragments.databinding.FragmentSecondBinding
import com.example.skillbox_hw_quiz.quiz.*
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"

/**
 * A simple [Fragment] subclass.
 * Use the [SecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondFragment : Fragment() {


    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var param3: String? = null

    var par1: String? = null
    var par2: String? = null
    var par3: String? = null

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            param3 = it.getString(ARG_PARAM3)
        }


       val q= QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[1]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)

        binding.radioGroup1.setOnCheckedChangeListener { radioGroup, i ->



            val selected = radioGroup.checkedRadioButtonId
            par1 = radioGroup.findViewById<RadioButton>(selected).text.toString()
            Toast.makeText(context, par1, Toast.LENGTH_SHORT).show()
        }

        binding.radioGroup2.setOnCheckedChangeListener { radioGroup, i ->
            val selected = radioGroup.checkedRadioButtonId
            par2 = radioGroup.findViewById<RadioButton>(selected).text.toString()
            Toast.makeText(context, par2, Toast.LENGTH_SHORT).show()
        }

        binding.radioGroup3.setOnCheckedChangeListener { radioGroup, i ->
            val selected = radioGroup.checkedRadioButtonId
            par3 = radioGroup.findViewById<RadioButton>(selected).text.toString()
            Toast.makeText(context, par3, Toast.LENGTH_SHORT).show()
        }

        binding.getAnswerButton.setOnClickListener {

            Toast.makeText(context, par1 + par2 + par3, Toast.LENGTH_SHORT).show()

        }

        return binding.root
    }

    private fun radioButton(radioGroup: RadioGroup): String? {

        var btn: String? = null
        //  var param = btn.text.toString()
        radioGroup.setOnCheckedChangeListener { radioGroup, i ->

            val selected = radioGroup.checkedRadioButtonId
            btn = radioGroup.findViewById<RadioButton>(selected).text.toString()
            Toast.makeText(context, btn, Toast.LENGTH_SHORT).show()


        }

        return btn
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SecondFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SecondFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

