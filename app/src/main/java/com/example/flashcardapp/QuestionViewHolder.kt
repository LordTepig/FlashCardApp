package com.example.flashcardapp

import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.flashcardapp.databinding.ListItemLayoutBinding

class QuestionViewHolder(val binding: ListItemLayoutBinding):
    RecyclerView.ViewHolder(binding.root)
    {

        private lateinit var currentQuestion: Question

        init {
            binding.root.setOnClickListener {   view ->
                Toast.makeText(
                    view.context, currentQuestion.question + "clicked",
                    Toast.LENGTH_LONG)
                    .show()
            }
        }
        fun bindQuestion(question: Question){
            currentQuestion = question
            binding.questionTextView.text = currentQuestion.question
            binding.answerTextView.text = currentQuestion.answer
        }
    }