package com.example.flashcardapp

import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.flashcardapp.databinding.ListItemLayoutBinding

class QuestionViewHolder(val binding: ListItemLayoutBinding):
    RecyclerView.ViewHolder(binding.root)
    {
        private lateinit var currentQuestion: Question

        init {
            binding.root.setOnClickListener {   view ->
                val action = StudyCardsFragmentDirections.actionStudyCardsFragmentToFlashCardFragment(currentQuestion.question, currentQuestion.answer)
                binding.root.findNavController().navigate(action)
            }
        }
        fun bindQuestion(question: Question){
            currentQuestion = question
            binding.questionTextView.text = "Question: ${currentQuestion.question}"
            binding.answerTextView.text = "Answer: ${currentQuestion.answer}"
        }
    }