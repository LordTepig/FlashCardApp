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
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.flashcardapp.databinding.FragmentCreateMultipleChoiceBinding
import com.example.flashcardapp.databinding.FragmentMainBinding
import kotlinx.coroutines.selects.select


class CreateMultipleChoiceFragment : Fragment() {
    private var _binding: FragmentCreateMultipleChoiceBinding? = null
    private val binding get() = _binding!!
    private val viewModel: QuestionViewModel by activityViewModels()
    lateinit var question: String
    lateinit var correctAnswer: String

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
        val answerObserver = Observer<String> { newAnswer->
            correctAnswer = newAnswer
        }

        viewModel.answer.observe(viewLifecycleOwner, answerObserver)


        MCSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            var selectedAnswer = ""

            override fun onItemSelected(adapterView: AdapterView<*>, childView: View?, position: Int, itemId: Long) {
                selectedAnswer = adapterView.getItemAtPosition(position).toString()
                correctAnswer = selectedAnswer
                viewModel.answer.setValue(selectedAnswer)
                viewModel.answer.observe(viewLifecycleOwner, answerObserver)
            }
            override fun onNothingSelected(adapterView: AdapterView<*>?) {
                viewModel.answer.setValue("A")
            }
        }
        binding.addMultipleChoiceCardButton.setOnClickListener {
            if(binding.multipleChoiceQuestionEditText.text.toString() == ""){
                Toast.makeText(context, R.string.no_question, Toast.LENGTH_LONG).show()
            }
            question = binding.multipleChoiceQuestionEditText.text.toString()

            when (correctAnswer){
                "A" ->  viewModel.answer.setValue(binding.multipleChoiceAnswerOneEditText.text.toString())
                "B" ->  viewModel.answer.setValue(binding.multipleChoiceQuestionTwoEditText.text.toString())
                "C" ->  viewModel.answer.setValue(binding.multipleChoiceQuestionThreeEditText.text.toString())
                "D" ->  viewModel.answer.setValue(binding.multipleChoiceQuestionFourEditText.text.toString())
            }

            if (((viewModel.answer.value.toString() == "") ) && (question != "")) {
                Toast.makeText(context, R.string.no_answer, Toast.LENGTH_LONG).show()
            }
            if((question != "")&& (viewModel.answer.value != "")){
                val action = CreateMultipleChoiceFragmentDirections.actionCreateMultipleChoiceFragmentToStudyCardsFragment(question, viewModel.answer.value.toString())
                rootView.findNavController().navigate(action)
            }

        }
        return rootView
    }

}