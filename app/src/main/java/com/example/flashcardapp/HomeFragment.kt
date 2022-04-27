package com.example.flashcardapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.flashcardapp.databinding.FragmentMainBinding


class HomeFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val rootView = binding.root

        binding.addFlashCard.setOnClickListener {

        }
        binding.addMultipleChoiceCard.setOnClickListener {

        }
        binding.addTrueFalseCard.setOnClickListener {

        }
        binding.studyCurrentQuestions.setOnClickListener {

        }
        return rootView
    }

}