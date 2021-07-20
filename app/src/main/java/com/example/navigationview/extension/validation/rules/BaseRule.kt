package com.example.navigationview.extension.validation.rules

interface BaseRule {

    fun validate(text: String): Boolean
    fun getErrorMessage(): String
    fun setError(msg: String)
}