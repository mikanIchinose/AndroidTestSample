package com.github.android.test.sample.game.model

import com.google.common.truth.Truth.assertThat
import com.google.common.truth.Truth.assertWithMessage
import org.junit.Test

class ScoreUnitTest {
    @Test
    fun whenIncrementingScore_shouldIncrementCurrentScore() {
        val score = Score()
        score.increment()
        assertWithMessage("Current score should have been 1")
            .that(score.current)
            .isEqualTo(1)
    }

    @Test
    fun whenIncrementingScore_shouldAlsoIncrementHighScore() {
        val score = Score()
        score.increment()
        assertThat(score.highest)
            .isEqualTo(1)
    }

    @Test
    fun whenIncrementingScore_belowHighScore_shouldNotIncrementHighScore() {
        val score = Score(10)
        score.increment()
        assertThat(score.highest)
            .isEqualTo(10)
    }
}