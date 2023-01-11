package com.github.android.test.sample.game.model

class Game(private val questions: List<Question>, highest: Int = 0) {
    var currentScore = 0
        private set

    var highestScore = highest
        private set

    private var questionIndex: Int = 0

    fun incrementScore() {
        currentScore++
        if (currentScore > highestScore) {
            highestScore = currentScore
        }
    }

    fun nextQuestion(): Question? {
        if (questionIndex < questions.size) {
            return questions[questionIndex++]
        }
        return null
    }

    fun answer(question: Question, option: String) {
        val result = question.answer(option)
        if (result) {
            incrementScore()
        }
    }
}