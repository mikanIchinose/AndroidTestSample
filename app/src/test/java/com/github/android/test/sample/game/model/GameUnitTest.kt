package com.github.android.test.sample.game.model

import com.google.common.truth.Truth.assertThat
import com.google.common.truth.Truth.assertWithMessage
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

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

    @Test
    fun whenAnswering_shouldDelegateToQuestion() {
        val question = mock<Question>()
        val game = Game(listOf(question))
        game.answer(question, "OPTION")

        // game.answerの中でquestion.answer("OPTION")が1回呼ばれたことを確認
        verify(question, times(1)).answer(eq("OPTION"))
    }

    @Test
    fun whenAnsweringCorrectly_shouldIncrementCurrentScore() {
        val question = mock<Question>()
        // stub化
        // question.answerを呼び出したときは常にtrueを返す
        whenever(question.answer(anyString())).thenReturn(true)

        val game = Game(listOf(question))
        // 正解をシミュレート
        game.answer(question, "OPTION")

        assertThat(game.currentScore)
            .isEqualTo(1)
    }

    @Test
    fun whenAnsweringIncorrectly_shouldNotIncrementCurrentScore() {
        val question = mock<Question>()
        // stub化
        // question.answerを呼び出したときは常にfalseを返す
        whenever(question.answer(anyString())).thenReturn(false)

        val game = Game(listOf(question))
        // 不正解をシミュレート
        game.answer(question, "OPTION")

        assertThat(game.currentScore)
            .isEqualTo(0)
    }
}