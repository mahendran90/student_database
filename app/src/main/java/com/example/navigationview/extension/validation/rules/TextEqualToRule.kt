package com.example.navigationview.extension.validation.rules

/**
 * Returns false if the text is not equal to the given text
 */
class TextEqualToRule(val target: String, var errorMsg: String = "Should be equal to $target") : BaseRule {

    override fun validate(text: String): Boolean {

        if (text.isEmpty())
            return false

        return text == target
    }

    override fun getErrorMessage(): String = errorMsg

    override fun setError(msg: String) {
        errorMsg = msg
    }
}