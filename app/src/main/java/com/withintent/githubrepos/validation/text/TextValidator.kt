package com.withintent.githubrepos.validation.text

import com.withintent.extension.empty
import com.withintent.githubrepos.validation.Validator

interface TextValidator : Validator<String?> {

    val errorMessage: String
        get() = String.empty
}