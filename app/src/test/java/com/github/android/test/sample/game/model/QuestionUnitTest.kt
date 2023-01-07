package com.github.android.test.sample.game.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
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
        assertNull(question.answeredOption)
    }

    @Test
    fun whenAnswering_shouldHaveAnsweredOption() {
        question.answer("INCORRECT")
        assertEquals("INCORRECT", question.answeredOption)
    }

    @Test
    fun whenAnswering_withCorrectOption_shouldReturnTrue() {
        val result = question.answer("CORRECT")
        assertTrue(result)
    }

    @Test
    fun whenAnswering_withIncorrectOption_shouldReturnTrue() {
        val result = question.answer("INCORRECT")
        assertFalse(result)
    }

    @Test(expected = IllegalArgumentException::class)
    fun whenAnswering_withInvalidOption_shouldThrowException() {
        question.answer("INVALID")
    }

    @Test
    fun whenGettingOption_shouldReturnOptionsWithCustomSort() {
        val options = question.getOptions { it.reversed() }
        assertEquals(listOf("INCORRECT", "CORRECT"), options)
    }
}