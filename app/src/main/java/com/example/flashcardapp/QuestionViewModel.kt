package com.example.flashcardapp

import androidx.lifecycle.ViewModel

class QuestionViewModel: ViewModel() {
    private var _questions = mutableListOf<Question>()
    val questions: MutableList<Question>
        get() = _questions

}