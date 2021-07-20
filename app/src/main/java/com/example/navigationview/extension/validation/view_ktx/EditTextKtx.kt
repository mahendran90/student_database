package com.example.navigationview.extension.validation.view_ktx

import android.widget.EditText
import com.example.navigationview.extension.validation.Validator

fun EditText.validator(): Validator {
    return Validator(text.toString())
}

fun EditText.nonEmpty(errorMsg: String? = null): Boolean {
    return validator().nonEmpty(errorMsg).check()
}

fun EditText.nonEmpty(callback: (message: String) -> Unit, errorMsg: String? = null): Boolean {
    return validator().nonEmpty(errorMsg)
            .addErrorCallback {
                callback.invoke(it)
            }
            .check()
}

fun EditText.nonEmpty(callback: (message: String) -> Unit): Boolean {
    return validator().nonEmpty()
            .addErrorCallback {
                callback.invoke(it)
            }
            .check()
}


fun EditText.minLength(minLength: Int, errorMsg: String? = null): Boolean {
    return validator().minLength(minLength, errorMsg).check()
}

fun EditText.minLength(minLength: Int, callback: (message: String) -> Unit, errorMsg: String? = null): Boolean {
    return validator().minLength(minLength, errorMsg)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

fun EditText.minLength(minLength: Int, callback: (message: String) -> Unit): Boolean {
    return validator().minLength(minLength)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

fun EditText.maxLength(maxLength: Int, errorMsg: String? = null): Boolean {
    return validator().maxLength(maxLength, errorMsg).check()
}

fun EditText.maxLength(maxLength: Int, callback: (message: String) -> Unit, errorMsg: String? = null): Boolean {
    return validator().maxLength(maxLength, errorMsg)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

fun EditText.maxLength(maxLength: Int, callback: (message: String) -> Unit): Boolean {
    return validator().maxLength(maxLength)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

fun EditText.validEmail(errorMsg: String? = null): Boolean {
    return validator().validEmail(errorMsg).check()
}

fun EditText.validEmail(callback: (message: String) -> Unit, errorMsg: String? = null): Boolean {
    return validator().validEmail(errorMsg)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

fun EditText.validEmail(callback: (message: String) -> Unit): Boolean {
    return validator().validEmail()
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

fun EditText.regex(pattern: String, errorMsg: String? = null): Boolean {
    return validator().regex(pattern, errorMsg).check()
}

fun EditText.regex(pattern: String, callback: (message: String) -> Unit, errorMsg: String? = null): Boolean {
    return validator().regex(pattern, errorMsg)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}

fun EditText.regex(pattern: String, callback: (message: String) -> Unit): Boolean {
    return validator().regex(pattern)
            .addErrorCallback {
                callback.invoke(it)
            }.check()
}