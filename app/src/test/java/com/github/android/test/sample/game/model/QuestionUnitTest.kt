package com.github.android.test.sample.game.model

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class QuestionUnitTest {
    private lateinit var question: Question

    @Before
    fun setup() {
        question = Question("CORRECT", "INCORRECT")
    }

    @Test
    fun whenCreatingQuestion_shouldNotHaveAnsweredOption() {
        assertThat(question.answeredOption).isNull()
    }

    @Test
    fun whenAnswering_shouldHaveAnsweredOption() {
        question.answer("INCORRECT")
        assertThat(question.answeredOption).isEqualTo("INCORRECT")
    }

    @Test
    fun whenAnswering_withCorrectOption_shouldReturnTrue() {
        val result = question.answer("CORRECT")
        assertThat(result).isTrue()
    }

    @Test
    fun whenAnswering_withIncorrectOption_shouldReturnTrue() {
        val result = question.answer("INCORRECT")
        assertThat(result).isFalse()
    }

    @Test(expected = IllegalArgumentException::class)
    fun whenAnswering_withInvalidOption_shouldThrowException() {
        question.answer("INVALID")
    }

    @Test
    fun whenGettingOption_shouldReturnOptionsWithCustomSort() {
        val options = question.getOptions { it.reversed() }
        assertThat(options)
            .isEqualTo(listOf("INCORRECT", "CORRECT"))
    }
}