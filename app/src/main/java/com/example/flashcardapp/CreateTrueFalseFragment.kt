package com.example.flashcardapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.flashcardapp.databinding.FragmentCreateFlashCardBinding
import com.example.flashcardapp.databinding.FragmentCreateMultipleChoiceBinding
import com.example.flashcardapp.databinding.FragmentCreateTrueFalseBinding
import com.google.android.material.snackbar.Snackbar


class CreateTrueFalseFragment : Fragment() {
    private var _binding: FragmentCreateTrueFalseBinding? = null
    private val binding get() = _binding!!
    private val viewModel: QuestionViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateTrueFalseBinding.inflate(inflater, container, false)
        val rootView = binding.root
        binding.currentTrueFalseAnswerTextView.text = ""
        val answerObserver = Observer<String> { newAnswer->
            binding.currentTrueFalseAnswerTextView.text = newAnswer.toString()
        }
        viewModel.answer.observe(viewLifecycleOwner, answerObserver)

        binding.trueAnswerButton.setOnClickListener {
            viewModel.answer.setValue("TRUE")
        }
        binding.falseAnswerButton.setOnClickListener {
            viewModel.answer.setValue("FALSE")
        }
        binding.addTrueFalseCardButton.setOnClickListener {
            if(binding.trueFalseQuestionEditText.text.toString() == "") {
                Snackbar.make(binding.myCoordinatorLayout,R.string.no_question, Snackbar.LENGTH_LONG).show()
            }
            else if (binding.currentTrueFalseAnswerTextView.text.toString() == ""){
                Snackbar.make(binding.myCoordinatorLayout, R.string.no_true_false_answer, Snackbar.LENGTH_LONG).show()
            }
            else {
                val question = binding.trueFalseQuestionEditText.text.toString()
                val action = CreateTrueFalseFragmentDirections.actionCreateTrueFalseFragmentToStudyCardsFragment(question, viewModel.answer.value.toString())
                rootView.findNavController().navigate(action)
            }
        }
        return rootView
    }

}