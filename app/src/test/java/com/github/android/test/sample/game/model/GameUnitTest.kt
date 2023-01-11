package com.github.android.test.sample.game.model

import com.google.common.truth.Truth.assertThat
import com.google.common.truth.Truth.assertWithMessage
import org.junit.Test

class GameUnitTest {
    @Test
    fun whenIncrementingScore_shouldIncrementCurrentScore() {
        val game = Game(emptyList(), 0)
        game.incrementScore()
        assertWithMessage("Current score should have be 1")
            .that(game.currentScore)
            .isEqualTo(1)
    }

    @Test
    fun whenIncrementingScore_shouldAlsoIncrementHighScore() {
        val game = Game(emptyList(), 0)
        game.incrementScore()
        assertThat(game.highestScore)
            .isEqualTo(1)
    }

    @Test
    fun whenIncrementingScore_belowHighScore_shouldNotIncrementHighScore() {
        val game = Game(emptyList(), 10)
        game.incrementScore()
        assertThat(game.highestScore)
            .isEqualTo(10)
    }

    @Test
    fun whenGettingNextQuestion_shouldReturnIt() {
        val question = Question("CORRECT", "INCORRECT")
        val questions = listOf(question)
        val game = Game(questions, 0)
        val nextQuestion = game.nextQuestion()
        assertThat(nextQuestion)
            .isSameInstanceAs(question)
    }

    @Test
    fun whenGettingNextQuestion_withoutMoreQuestions_shouldReturnNull() {
        val question = Question("CORRECT", "INCORRECT")
        val questions = listOf(question)
        val game = Game(questions, 0)
        game.nextQuestion()
        val nextQuestion = game.nextQuestion()
        assertThat(nextQuestion).isNull()
    }
}