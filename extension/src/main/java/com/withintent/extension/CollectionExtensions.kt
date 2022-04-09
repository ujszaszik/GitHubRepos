package com.withintent.extension

fun <T> List<T>.second(): T {
    if (size < 2)
        throw NoSuchElementException("List does not have at least two elements.")
    return this[1]
}