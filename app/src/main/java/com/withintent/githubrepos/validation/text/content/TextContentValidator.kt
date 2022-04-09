package com.withintent.githubrepos.validation.text.content

import com.withintent.githubrepos.validation.text.TextValidator

class TextContentValidator(private val allowedChars: List<Char>) : TextValidator {

    override fun isValid(value: String?): Boolean {
        return value?.all { char ->
            char in allowedChars
        } ?: false
    }
}
