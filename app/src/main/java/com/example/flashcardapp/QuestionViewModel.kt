package com.example.flashcardapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuestionViewModel: ViewModel() {
    private var _questions = mutableListOf<Question>()
    val questions: MutableList<Question>
        get() = _questions
//    private var _answer = MutableLiveData("")
//    val answer: LiveData<String>
//        get() = _answer
    val answer: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
}

    fun addQuestion(question: Question){
        _questions.add(question)
    }




}