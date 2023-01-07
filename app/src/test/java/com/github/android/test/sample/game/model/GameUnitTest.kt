package com.github.android.test.sample.game.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertSame
import org.junit.Test

class GameUnitTest {
    @Test
    fun whenIncrementingScore_shouldIncrementCurrentScore() {
        val game = Game(emptyList(), 0)
        game.incrementScore()
        assertEquals("Current score should have be 1", 1, game.currentScore)
    }

    @Test
    fun whenIncrementingScore_shouldAlsoIncrementHighScore() {
        val game = Game(emptyList(), 0)
        game.incrementScore()
        assertEquals(1, game.highestScore)
    }

    @Test
    fun whenIncrementingScore_belowHighScore_shouldNotIncrementHighScore() {
        val game = Game(emptyList(), 10)
        game.incrementScore()
        assertEquals(10, game.highestScore)
    }

    @Test
    fun whenGettingNextQuestion_shouldReturnIt() {
        val question = Question("CORRECT", "INCORRECT")
        val questions = listOf(question)
        val game = Game(questions, 0)
        val nextQuestion = game.nextQuestion()
        assertSame(question, nextQuestion)
    }

    @Test
    fun whenGettingNextQuestion_withoutMoreQuestions_shouldReturnNull() {
        val question = Question("CORRECT", "INCORRECT")
        val questions = listOf(question)
        val game = Game(questions, 0)
        game.nextQuestion()
        val nextQuestion = game.nextQuestion()
        assertNull(nextQuestion)
    }
}