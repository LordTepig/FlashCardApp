package com.example.flashcardapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.flashcardapp.databinding.FragmentCreateFlashCardBinding
import com.example.flashcardapp.databinding.FragmentMainBinding


class CreateFlashCardFragment : Fragment() {
    private var _binding: FragmentCreateFlashCardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateFlashCardBinding.inflate(inflater, container, false)
        val rootView = binding.root

        binding.addFlashCardButton.setOnClickListener {
            val question = binding.flashCardQuestionEditText.text.toString()
            val answer = binding.answerEditText.text.toString()
            val action = CreateFlashCardFragmentDirections.actionCreateFlashCardFragmentToStudyCardsFragment(question, answer)
            rootView.findNavController().navigate(action)
        }

        return rootView
    }

}