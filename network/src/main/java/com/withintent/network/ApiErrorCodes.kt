package com.withintent.network

enum class ApiErrorCodes(val code: Int, val message: String) {
    DEFAULT(0, "Unexpected error happened during network call."),
    NO_CONNECTION(1, "There is no internet connection."),
    TIMEOUT(2, "The request has timed out."),
    REQUEST_RATE_EXCEEDED(3, "The request limit has been exceeded.");
}