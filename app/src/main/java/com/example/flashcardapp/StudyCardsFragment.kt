package com.example.flashcardapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.flashcardapp.databinding.FragmentStudyCardsBinding

class StudyCardsFragment : Fragment() {
    private val viewModel: QuestionViewModel by viewModels()
    private var _binding: FragmentStudyCardsBinding? = null
    private val binding get() = _binding!!

    val questions  = mutableListOf<Question>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStudyCardsBinding.inflate(inflater, container, false)
        val rootView = binding.root

        val args = StudyCardsFragmentArgs.fromBundle(requireArguments())
        val ques = Question(args.questionArg, args.answerArg)
        questions.add(ques) //default question

        val adapter = QuestionAdapter(questions)
        binding.recyclerView.adapter = adapter

        binding.returnHomeButton.setOnClickListener {
            val action = StudyCardsFragmentDirections.actionStudyCardsFragmentToHomeFragment()
            rootView.findNavController().navigate(action)
        }
        return rootView
    }

}