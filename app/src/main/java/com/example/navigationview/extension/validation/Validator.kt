package com.example.navigationview.extension.validation

import com.example.navigationview.extension.validation.rules.*

/**
 * The core Validator builder class for validation operations and checks!
 *
 * This class allows developers to process single or multiple validation checks on input views.
 *
 */
class Validator(val text: String) {
    /*
     * Boolean to determine whether all the validations have passed successfully!
     * If any validation check is failed, then the value to
     * false and result is returned to developer
     */
    private var isValid = true

    /*
     The error message to be sent in the error callback
     */
    private var errorMessage: String = ""

    /*
     * In case of validation error or failure, this callback is invoked
     */
    var errorCallback: ((message: String) -> Unit)? = null

    /*
     * In case of validation success, this callback is invoked
     */
    var successCallback: (() -> Unit)? = null

    /*
     * The rules list to check for validation
     */
    var rulesList = ArrayList<BaseRule>()

    /*
     * Performs the validation check and returns true or false.
     * Also invokes success and error callbacks if non null.
     */
    fun check(): Boolean {
        for (rule in rulesList) {
            if (!rule.validate(text)) {
                setError(rule.getErrorMessage())
                break
            }
        }

        // Invoking callbacks
        if (isValid)
            successCallback?.invoke()
        else
            errorCallback?.invoke(errorMessage)

        return isValid
    }

    fun setError(message: String) {
        isValid = false
        errorMessage = message
    }

    fun addRule(rule: BaseRule): Validator {
        rulesList.add(rule)
        return this
    }

    fun addErrorCallback(callback: (message: String) -> Unit): Validator {
        errorCallback = callback
        return this
    }

    fun addSuccessCallback(callback: () -> Unit): Validator {
        successCallback = callback
        return this
    }

    // Rules
    fun nonEmpty(errorMsg: String? = null): Validator {
        val rule = errorMsg?.let { NonEmptyRule(errorMsg) } ?: NonEmptyRule()
        addRule(rule)
        return this
    }

    fun minLength(length: Int, errorMsg: String? = null): Validator {
        val rule = errorMsg?.let { MinLengthRule(length, errorMsg) } ?: MinLengthRule(length)
        addRule(rule)
        return this
    }

    fun maxLength(length: Int, errorMsg: String? = null): Validator {
        val rule = errorMsg?.let { MaxLengthRule(length, errorMsg) } ?: MaxLengthRule(length)
        addRule(rule)
        return this
    }

    fun validEmail(errorMsg: String? = null): Validator {
        val rule = errorMsg?.let { EmailRule(errorMsg) } ?: EmailRule()
        addRule(rule)
        return this
    }

    fun regex(pattern: String, errorMsg: String? = null): Validator {
        val rule = errorMsg?.let { RegexRule(pattern, errorMsg) } ?: RegexRule(pattern)
        addRule(rule)
        return this
    }

    fun textEqualTo(target: String, errorMsg: String? = null): Validator {
        val rule = errorMsg?.let { TextEqualToRule(target, errorMsg) } ?: TextEqualToRule(target)
        addRule(rule)
        return this
    }

    fun onlyNumbers(errorMsg: String? = null): Validator {
        val rule = errorMsg?.let { OnlyNumbersRule(errorMsg) } ?: OnlyNumbersRule()
        addRule(rule)
        return this
    }

    fun textNotEqualTo(target: String, errorMsg: String? = null): Validator {
        val rule = errorMsg?.let { TextNotEqualToRule(target, errorMsg) }
                ?: TextNotEqualToRule(target)
        addRule(rule)
        return this
    }
}