package com.example.myapplication

class MultipleChoice (
    questionString: String,
    private val options: List<String>,
    private val correctAnswer: String
) : Question(questionString) {

    override fun display(): String {
        TODO("Not yet implemented")
    }

    override fun checkAnswer(answer: String): Boolean {
        return answer.equals(correctAnswer.toString(), true)
    }

}