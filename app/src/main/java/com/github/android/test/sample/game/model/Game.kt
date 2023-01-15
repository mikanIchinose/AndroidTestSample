package com.github.android.test.sample.game.model

class Game(private val questions: List<Question>, val score: Score = Score(0)) {
    private var questionIndex: Int = 0

    fun nextQuestion(): Question? {
        if (questionIndex < questions.size) {
            return questions[questionIndex++]
        }
        return null
    }

    fun answer(question: Question, option: String) {
        val result = question.answer(option)
        if (result) {
            score.increment()
        }
    }
}