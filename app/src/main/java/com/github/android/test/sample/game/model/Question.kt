package com.github.android.test.sample.game.model

class Question(
    val correctOption: String,
    val incorrectOption: String
) {
    var answeredOption: String? = null
        private set

    val isAnsweredCorrectly: Boolean
        get() = correctOption == answeredOption

    fun answer(option: String): Boolean {
        if (option != correctOption && option != incorrectOption)
            throw IllegalArgumentException("Not a valid option")

        answeredOption = option

        return isAnsweredCorrectly
    }

    fun getOptions(strategy: (List<String>) -> List<String> = { it.shuffled() }): List<String> =
        strategy(listOf(correctOption, incorrectOption))
}
