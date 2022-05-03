package com.example.flashcardapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.flashcardapp.databinding.FragmentStudyCardsBinding

class StudyCardsFragment : Fragment() {
    private val viewModel: QuestionViewModel by viewModels()
    private var _binding: FragmentStudyCardsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStudyCardsBinding.inflate(inflater, container, false)
        val rootView = binding.root

        val args = StudyCardsFragmentArgs.fromBundle(requireArguments())
        val questions = listOf(Question(args.questionArg, args.answerArg)) //default question

        val adapter = QuestionAdapter(questions)
        binding.recyclerView.adapter = adapter

        return rootView
    }

}