package com.withintent.githubrepos.validation

interface Validator<Type> {

    fun isValid(value: Type): Boolean
}