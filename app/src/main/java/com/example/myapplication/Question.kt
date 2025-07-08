package com.example.myapplication

abstract class Question (val questionString: String): Displayable{
    abstract fun checkAnswer(answer: String): Boolean
}