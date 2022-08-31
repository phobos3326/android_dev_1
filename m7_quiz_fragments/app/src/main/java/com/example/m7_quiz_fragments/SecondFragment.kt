package com.example.m7_quiz_fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.m7_quiz_fragments.databinding.FragmentSecondBinding
import com.example.skillbox_hw_quiz.quiz.*


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

    var par1: String? = null
    var par2: String? = null
    var par3: String? = null

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)

        /*binding.question1.text = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[0].question
        binding.question2.text = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[1].question
        binding.question3.text = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[2].question*/

        binding.getAnswerButton.isEnabled = false

        val listOfTextView = listOf(
            binding.question1,
            binding.question2,
            binding.question3,
        )

        val listOfRadioButton = listOf(
            binding.radioButton1,
            binding.radioButton2,
            binding.radioButton3,
            binding.radioButton4,
            binding.radioButton5,
            binding.radioButton6,
            binding.radioButton7,
            binding.radioButton8,
            binding.radioButton9,
            binding.radioButton10,
            binding.radioButton11,
            binding.radioButton12
        )

        for (i in listOfTextView.indices) {
            listOfTextView[i].text =
                QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[i].question
        }

        val answersOne = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[0].answers
        val listOfRadiobuttonOne = listOfRadioButton.slice(0..3)
        val answersTwo = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[1].answers
        val listOfRadiobuttonTwo = listOfRadioButton.slice(4..7)
        val answersThree = QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[2].answers
        val listOfRadiobuttonThree = listOfRadioButton.slice(8..11)

        param1 = function(listOfRadiobuttonOne, answersOne)
        param2 = function(listOfRadiobuttonTwo, answersTwo)
        param3 = function(listOfRadiobuttonThree, answersThree)


        binding.radioGroup1.setOnCheckedChangeListener { radioGroup, i ->
            val selected = radioGroup.checkedRadioButtonId
            par1 = radioGroup.findViewById<RadioButton>(selected).text.toString()

            Toast.makeText(context, par1, Toast.LENGTH_SHORT).show()
            isAnswered()
        }

        binding.radioGroup2.setOnCheckedChangeListener { radioGroup, i ->
            val selected = radioGroup.checkedRadioButtonId
            par2 = radioGroup.findViewById<RadioButton>(selected).text.toString()
            Toast.makeText(context, par2, Toast.LENGTH_SHORT).show()
            isAnswered()
        }

        binding.radioGroup3.setOnCheckedChangeListener { radioGroup, i ->
            val selected = radioGroup.checkedRadioButtonId
            par3 = radioGroup.findViewById<RadioButton>(selected).text.toString()
            Toast.makeText(context, par3, Toast.LENGTH_SHORT).show()
            isAnswered()
        }

        binding.getAnswerButton.setOnClickListener {
            Toast.makeText(context, """$param1, $param2, $param3, """, Toast.LENGTH_SHORT).show()

        }

        return binding.root
    }

    private fun isAnswered() {
        if (par1 != null && par2 != null && par3 != null) {
            binding.getAnswerButton.isEnabled = true
        }
    }

    private fun function(
        radioButton: List<RadioButton>,
        answers: List<String>
    ): Int? {

        var index: Int? = null
        for (i in radioButton.indices) {
            radioButton[i].text = answers[i]
            index=i
        }
        return index
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
        fun newInstance(param1: Int, param2: Int, param3: Int) =
            SecondFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                    putInt(ARG_PARAM2, param2)
                    putInt(ARG_PARAM3, param3)
                }
            }
    }
}

