package com.example.flashcardapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.flashcardapp.databinding.FragmentStudyCardsBinding

class StudyCardsFragment : Fragment() {
    private val viewModel: QuestionViewModel by activityViewModels()
    private var _binding: FragmentStudyCardsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStudyCardsBinding.inflate(inflater, container, false)
        val rootView = binding.root

        val args = StudyCardsFragmentArgs.fromBundle(requireArguments())
        if((args.questionArg != "") && (args.answerArg != "")){ //checks that there is not a blank question or answer
            val ques = Question(args.questionArg, args.answerArg)
            viewModel.addQuestion(ques)   //adds the question and answer passed through navigation to the mutable list
        }

        val adapter = QuestionAdapter(viewModel.questions)
        binding.recyclerView.adapter = adapter

        binding.returnHomeButton.setOnClickListener {
            val action = StudyCardsFragmentDirections.actionStudyCardsFragmentToHomeFragment()
            rootView.findNavController().navigate(action)
        }

        return rootView
    }

}