package com.example.myapplication

class TrueFalseQuestion (
    questionString: String,
    private val correctAnswer: Boolean, ): Question(questionString)
{
    override fun display(): String {
        TODO("Not yet implemented")
    }

    override fun checkAnswer(answer: String): Boolean {
        return answer.equals(correctAnswer.toString(), true)
    }

}