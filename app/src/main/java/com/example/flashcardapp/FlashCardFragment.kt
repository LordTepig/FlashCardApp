package com.example.flashcardapp

import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.findNavController
import com.example.flashcardapp.databinding.FragmentFlashCardBinding
import com.example.flashcardapp.databinding.FragmentStudyCardsBinding


class FlashCardFragment : Fragment() {

    private var _binding: FragmentFlashCardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFlashCardBinding.inflate(inflater, container, false)
        val rootView = binding.root

        val args = FlashCardFragmentArgs.fromBundle(requireArguments())
        val question = args.questionArg
        val answer = args.answerArg
        binding.flashCardTextView.text = question
        binding.flashCardTextView.setOnClickListener {
            val text = binding.flashCardTextView.text.toString()
            if (text == question){ //change the text from question to answer when clicked (question -> answer)
                binding.flashCardTextView.text = answer
            }
            else if (text == answer){ //change the text from answer to question when clicked (answer -> question)
                binding.flashCardTextView.text = question
            }
        }
        binding.flashCardPhotoCameraButton.setOnClickListener{
            val action = FlashCardFragmentDirections.actionFlashCardFragmentToCameraFragment()
            rootView.findNavController().navigate(action)
        }
        setFragmentResultListener("requestKey"){ requestKey, bundle ->
            val result = bundle.get("bundleKey")
            binding.flashCardPhotoImageView.setImageBitmap(result as Bitmap)
        }
        return rootView
    }

}