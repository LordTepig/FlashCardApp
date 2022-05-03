package com.example.flashcardapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.flashcardapp.databinding.FragmentCreateFlashCardBinding
import com.example.flashcardapp.databinding.FragmentCreateMultipleChoiceBinding
import com.example.flashcardapp.databinding.FragmentCreateTrueFalseBinding


class CreateTrueFalseFragment : Fragment() {
    private var _binding: FragmentCreateTrueFalseBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateTrueFalseBinding.inflate(inflater, container, false)
        val rootView = binding.root

        return rootView
    }

}