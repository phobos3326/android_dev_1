package com.example.m9_quiz_localization

import android.animation.ObjectAnimator
import android.content.ContentValues.TAG
import android.content.res.Configuration
import android.content.res.Resources
import android.icu.util.ULocale.getLanguage
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.core.content.contentValuesOf
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.m9_quiz_localization.databinding.FragmentSecondBinding
import com.example.m9_quiz_localization.quiz.QuizStorage
import com.google.android.material.snackbar.Snackbar
import java.util.*
import kotlin.concurrent.fixedRateTimer


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private val ARG_PARAM1 = null
private val ARG_PARAM2 = null
private val ARG_PARAM3 = null

/**
 * A simple [Fragment] subclass.
 * Use the [SecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondFragment : Fragment() {


    // TODO: Rename and change types of parameters
    private var param1: Int? = null
    private var param2: Int? = null
    private var param3: Int? = null



    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
            param2 = it.getInt(ARG_PARAM2)
            param3 = it.getInt(ARG_PARAM3)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val builder: NavOptions.Builder = NavOptions.Builder()
                    val navOptions: NavOptions =
                        builder.setEnterAnim(R.animator.slide_top_bottom_top)
                            .setExitAnim(android.R.anim.slide_out_right).build()
                    findNavController().navigate(
                        R.id.action_secondFragment_to_firstFragment,
                        null,
                        navOptions
                    )
                }
            }
        )




        _binding = FragmentSecondBinding.inflate(inflater, container, false)

        binding.getAnswerButton.isEnabled = false

        val listOfTextView = listOf(
            binding.question1,
            binding.question2,
            binding.question3,
        )

        val listOfRadioButton1 = listOf(
            binding.radioButton1,
            binding.radioButton2,
            binding.radioButton3,
            binding.radioButton4,

            )
        val listOfRadioButton2 = listOf(
            binding.radioButton5,
            binding.radioButton6,
            binding.radioButton7,
            binding.radioButton8,
        )

        val listOfRadioButton3 = listOf(
            binding.radioButton9,
            binding.radioButton10,
            binding.radioButton11,
            binding.radioButton12
        )


        var answersOne: List<String>
        var answersTwo: List<String>
        var answersThree: List<String>




        val a = Locale.getDefault().getLanguage()

        if (a == "ru") {
            for (i in listOfTextView.indices) {
                listOfTextView[i].text =
                    QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[i].question
                answersOne = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[0].answers
                answersTwo = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[1].answers
                answersThree = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[2].answers

                getAnswerToRadioButton(listOfRadioButton1, answersOne)
                getAnswerToRadioButton(listOfRadioButton2, answersTwo)
                getAnswerToRadioButton(listOfRadioButton3, answersThree)

            }
        } else {
            for (i in listOfTextView.indices) {
                listOfTextView[i].text =
                    QuizStorage.getQuiz(QuizStorage.Locale.En).questions[i].question

                answersOne = QuizStorage.getQuiz(QuizStorage.Locale.En).questions[0].answers
                answersTwo = QuizStorage.getQuiz(QuizStorage.Locale.En).questions[1].answers
                answersThree = QuizStorage.getQuiz(QuizStorage.Locale.En).questions[2].answers

                getAnswerToRadioButton(listOfRadioButton1, answersOne)
                getAnswerToRadioButton(listOfRadioButton2, answersTwo)
                getAnswerToRadioButton(listOfRadioButton3, answersThree)


            }
        }



        binding.radioGroup1.setOnCheckedChangeListener { radioGroup, i ->
            param1 = radioGroupResult(radioGroup, listOfRadioButton1)
            isAnswered()
        }
        binding.radioGroup2.setOnCheckedChangeListener { radioGroup, i ->
            param2 = radioGroupResult(radioGroup, listOfRadioButton2)
            isAnswered()
        }
        binding.radioGroup3.setOnCheckedChangeListener { radioGroup, i ->
            param3 = radioGroupResult(radioGroup, listOfRadioButton3)
            isAnswered()
        }

        binding.getAnswerButton.setOnClickListener {
            Toast.makeText(context, """$param1, $param2, $param3 """, Toast.LENGTH_SHORT).show()
            val bundle = bundleOf(
                "answer1" to param1,
                "answer2" to param2,
                "answer3" to param3
            )

            val builder: NavOptions.Builder = NavOptions.Builder()

            val navOptions: NavOptions =
                builder.setEnterAnim(R.animator.slide_top_bottom_top)
                    .setExitAnim(android.R.anim.slide_out_right).build()
            findNavController().navigate(
                R.id.action_secondFragment_to_thirdFragment,
                bundle,
                navOptions
            )

        }

        binding.getBack.setOnClickListener {
            val builder: NavOptions.Builder = NavOptions.Builder()
            val navOptions = builder.setEnterAnim(R.animator.slide_top_bottom_top).build()
            findNavController().navigate(
                R.id.action_secondFragment_to_firstFragment,
                null,
                navOptions
            )
        }

        alpha(binding.getBack)
        alpha(binding.getAnswerButton)
        return binding.root
    }

    private fun alpha(view: View) {
        ObjectAnimator.ofFloat(view, "alpha", 0f, 1f).apply {
            duration = 3000
            start()
        }
    }

    private fun radioGroupResult(
        radioGroup: RadioGroup,
        listOfRadioButton: List<RadioButton>
    ): Int? {
        var par: Int? = null
        val selected = radioGroup.checkedRadioButtonId
        listOfRadioButton.indices.forEach { i ->
            if (listOfRadioButton[i].id == selected) {
                par = i
            }
        }
        listOfRadioButton.find { it.id == selected }

        return par
    }

    private fun isAnswered() {
        if (param1 != null && param2 != null && param3 != null) {
            binding.getAnswerButton.isEnabled = true
        }
    }

    private fun getAnswerToRadioButton(
        radioButtonList: List<RadioButton>,
        answers: List<String>
    ) {
        for (i in radioButtonList.indices) {
            radioButtonList[i].text = answers[i]
        }
        return
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
        fun newInstance(param1: Int, param2: Int, param3: Int) =
            SecondFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                    putInt(ARG_PARAM2, param2)
                    putInt(ARG_PARAM3, param3)
                }
            }
    }

    override fun onDestroy() {
        Log.d("Fragment1", "onDestroy")
        _binding = null
        super.onDestroy()
    }


}