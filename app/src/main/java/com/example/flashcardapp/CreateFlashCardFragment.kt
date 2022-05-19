package com.example.flashcardapp

import android.app.ProgressDialog.show
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.flashcardapp.databinding.FragmentCreateFlashCardBinding
import com.example.flashcardapp.databinding.FragmentMainBinding
import com.google.android.material.snackbar.Snackbar


class CreateFlashCardFragment : Fragment() {
    private var _binding: FragmentCreateFlashCardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateFlashCardBinding.inflate(inflater, container, false)
        val rootView = binding.root
//        lateinit var question: String
        binding.addFlashCardButton.setOnClickListener {
//            while(binding.flashCardQuestionEditText.text.toString() != ""){
//                Toast.makeText(context, R.string.no_question, Toast.LENGTH_LONG).show()
//                //display a message that says the user needs to input text for the question
//                }
//            while(binding.answerEditText.text.toString() != ""){
//                Toast.makeText(context, R.string.no_answer, Toast.LENGTH_LONG).show()
//            }
            val question = binding.flashCardQuestionEditText.text.toString()
            val answer = binding.answerEditText.text.toString()
            if ((question != "")&& (answer != "")){
                val action = CreateFlashCardFragmentDirections.actionCreateFlashCardFragmentToStudyCardsFragment(question, answer)
                rootView.findNavController().navigate(action)
            }
            else{
                if(binding.flashCardQuestionEditText.text.toString() == "") {
                    Snackbar.make(binding.myCoordinatorLayout,R.string.no_question, Snackbar.LENGTH_LONG).show()
                }
                else if(binding.answerEditText.text.toString() == ""){
                    Snackbar.make(binding.myCoordinatorLayout,R.string.no_answer, Snackbar.LENGTH_LONG).show()
                }
            }
        }
        return rootView
    }

}