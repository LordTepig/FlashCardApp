package com.example.flashcardapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.flashcardapp.databinding.FragmentCreateMultipleChoiceBinding
import com.example.flashcardapp.databinding.FragmentMainBinding


class CreateMultipleChoiceFragment : Fragment() {
    private var _binding: FragmentCreateMultipleChoiceBinding? = null
    private val binding get() = _binding!!
    lateinit var correctAnswer: String
    lateinit var question: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateMultipleChoiceBinding.inflate(inflater, container, false)
        val rootView = binding.root

        val MCSpinner : Spinner = binding.correctAnswerSpinner
        val correctAnswerMCAdapter= ArrayAdapter.createFromResource(
            requireContext(),
            R.array.correct_answers_MC_array,
            android.R.layout.simple_spinner_item)

        correctAnswerMCAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        MCSpinner.adapter = correctAnswerMCAdapter

        MCSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            var selectedAnswer = ""

            override fun onItemSelected(adapterView: AdapterView<*>, childView: View?, position: Int, itemId: Long) {
                selectedAnswer = adapterView.getItemAtPosition(position).toString()
                correctAnswer = selectedAnswer
            }
            override fun onNothingSelected(adapterView: AdapterView<*>?) {
                correctAnswer = "A"
            }
        }
        binding.addMultipleChoiceCardButton.setOnClickListener {
            if(binding.multipleChoiceQuestionEditText.text.toString() == ""){
                Toast.makeText(context, R.string.no_question, Toast.LENGTH_LONG).show()
            }
            question = binding.multipleChoiceQuestionEditText.text.toString()
            when (correctAnswer){
                "A" -> correctAnswer = binding.multipleChoiceAnswerOneEditText.text.toString()
                "B" -> correctAnswer = binding.multipleChoiceQuestionTwoEditText.text.toString()
                "C" -> correctAnswer = binding.multipleChoiceQuestionThreeEditText.text.toString()
                "D" -> correctAnswer = binding.multipleChoiceQuestionFourEditText.text.toString()
            }
            if ((correctAnswer == "") && (question != "")) {
                Toast.makeText(context, R.string.no_answer, Toast.LENGTH_LONG).show()
            }
            if((question != "")&& (correctAnswer != "")){
                val action = CreateMultipleChoiceFragmentDirections.actionCreateMultipleChoiceFragmentToStudyCardsFragment(question, correctAnswer)
                rootView.findNavController().navigate(action)
            }

        }
        return rootView
    }

}