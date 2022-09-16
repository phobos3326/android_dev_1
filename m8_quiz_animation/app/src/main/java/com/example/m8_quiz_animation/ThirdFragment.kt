package com.example.m8_quiz_animation

import android.animation.AnimatorInflater
import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.m8_quiz_animation.databinding.FragmentThirdBinding
import com.example.skillbox_hw_quiz.quiz.QuizStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass.
 * Use the [ThirdFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ThirdFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentThirdBinding? = null
    private val binding get() = _binding!!

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


        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().navigate(R.id.action_thirdFragment_to_firstFragment)
                }
            }

        )
        // Inflate the layout for this fragment
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        val feedback1 = arguments?.getInt("answer1")
        val feedback2 = arguments?.getInt("answer2")
        val feedback3 = arguments?.getInt("answer3")
        binding.answer1.text =
            QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[0].feedback[feedback1!!]
        binding.answer2.text =
            QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[1].feedback[feedback2!!]
        binding.answer3.text =
            QuizStorage.getQuiz(QuizStorage.Locale.Ru).questions[2].feedback[feedback3!!]

        binding.startOverButton.setOnClickListener {

            val builder: NavOptions.Builder = NavOptions.Builder()
            val navOptions = builder.setEnterAnim(R.animator.alpha).build()

            findNavController().navigate(
                R.id.action_thirdFragment_to_secondFragment,
                null,
                navOptions
            )
        }


        val scaleAnimation = AnimatorInflater.loadAnimator(
            context, R.animator.scale)
        scaleAnimation.setTarget(binding.imageView3)
        scaleAnimation.start()




        return binding.root
    }

    private fun alphaAnim(view: View) {
        ObjectAnimator.ofFloat(view, "alpha", 0f, 1f).apply {
            duration = 2000
            start()
        }
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ThirdFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ThirdFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onDestroyView() {

        _binding = null
        // findNavController().navigate(R.id.action_thirdFragment_to_firstFragment2)
        super.onDestroyView()

    }

}