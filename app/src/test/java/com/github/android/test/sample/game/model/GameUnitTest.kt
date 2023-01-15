package com.github.android.test.sample.game.model

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GameUnitTest {
    @Test
    fun whenGettingNextQuestion_shouldReturnIt() {
        val question = Question("CORRECT", "INCORRECT")
        val questions = listOf(question)
        val score = mock<Score>()
        val game = Game(questions, score)
        val nextQuestion = game.nextQuestion()
        assertThat(nextQuestion)
            .isSameInstanceAs(question)
    }

    @Test
    fun whenGettingNextQuestion_withoutMoreQuestions_shouldReturnNull() {
        val question = Question("CORRECT", "INCORRECT")
        val questions = listOf(question)
        val score = mock<Score>()
        val game = Game(questions, score)
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
        val score = mock<Score>()

        val game = Game(listOf(question), score)
        // 正解をシミュレート
        game.answer(question, "OPTION")

        // gameはスコア計算をScoreに任せるようになったので正しい値になっていることの確認ではなく、
        // score.increment()が呼び出されているかを確認する
        // score.increment()が正しく実行できるかどうかはScoreUnitTestでテストするから大丈夫
        // state -> behavior
        verify(score).increment()
    }

    @Test
    fun whenAnsweringIncorrectly_shouldNotIncrementCurrentScore() {
        val question = mock<Question>()
        // stub化
        // question.answerを呼び出したときは常にfalseを返す
        whenever(question.answer(anyString())).thenReturn(false)
        val score = mock<Score>()

        val game = Game(listOf(question), score)
        // 不正解をシミュレート
        game.answer(question, "OPTION")

        // gameはスコア計算をScoreに任せるようになったので正しい値になっていることの確認ではなく、
        // score.increment()が呼び出されていないことを確認する
        // state -> behavior
        verify(score, never()).increment()
    }
}