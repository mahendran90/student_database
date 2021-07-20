package com.example.navigationview.extension.validation.view_ktx

import com.example.navigationview.extension.validation.Validator


fun String.validator(): Validator {
    return Validator(this)
}

fun String.nonEmpty(errorMsg: String? = null): Boolean {
    return validator().nonEmpty(errorMsg).check()
}

fun String.nonEmpty(callback: (message: String) -> Unit, errorMsg: String? = null): Boolean {
    return validator().nonEmpty(errorMsg)
            .addErrorCallback {
                callback.invoke(it)
            }
            .check()
}

fun String.nonEmpty(callback: (message: String) -> Unit): Boolean {
    return validator().nonEmpty()
            .addErrorCallback {
                callback.invoke(it)
            }
            .check()
}

fun String.minLength(minLength: Int, errorMsg: String? = null): Boolean {
    return validator().minLength(minLength, errorMsg).check()
}

fun String.minLength(minLength: Int, callback: (message: String) -> Unit, errorMsg: String? = null): Boolean {
    return validator().minLength(minLength, errorMsg)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

fun String.minLength(minLength: Int, callback: (message: String) -> Unit): Boolean {
    return validator().minLength(minLength)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

fun String.maxLength(maxLength: Int, errorMsg: String? = null): Boolean {
    return validator().maxLength(maxLength, errorMsg).check()
}

fun String.maxLength(maxLength: Int, callback: (message: String) -> Unit, errorMsg: String? = null): Boolean {
    return validator().maxLength(maxLength, errorMsg)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

fun String.maxLength(maxLength: Int, callback: (message: String) -> Unit): Boolean {
    return validator().maxLength(maxLength)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

fun String.validEmail(errorMsg: String? = null): Boolean {
    return validator().validEmail(errorMsg).check()
}

fun String.validEmail(callback: (message: String) -> Unit, errorMsg: String? = null): Boolean {
    return validator().validEmail(errorMsg)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

fun String.validEmail(callback: (message: String) -> Unit): Boolean {
    return validator().validEmail()
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

fun String.regex(pattern: String, errorMsg: String? = null): Boolean {
    return validator().regex(pattern, errorMsg).check()
}

fun String.regex(pattern: String, callback: (message: String) -> Unit, errorMsg: String? = null): Boolean {
    return validator().regex(pattern, errorMsg)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

fun String.regex(pattern: String, callback: (message: String) -> Unit): Boolean {
    return validator().regex(pattern)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}