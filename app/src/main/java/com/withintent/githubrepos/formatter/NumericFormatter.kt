package com.withintent.githubrepos.formatter

import com.withintent.extension.isNegative
import com.withintent.extension.isSmallerThan

object NumericFormatter {

    private const val NOT_AVAILABLE = "N/A"
    private const val POSTFIX_THOUSAND = "k"
    private const val POSTFIX_MILLION = "m"
    private const val THOUSAND = 1_000
    private const val MILLION = 1_000_000
    private const val BILLION = 1_000_000_000

    fun format(value: Number): String {
        return when {
            value.isNegative() -> NOT_AVAILABLE
            value.isSmallerThan(THOUSAND) -> "$value"
            value.isSmallerThan(MILLION) -> {
                getFormattedText(value.toDouble() / THOUSAND, POSTFIX_THOUSAND)
            }
            value.isSmallerThan(BILLION) -> {
                getFormattedText(value.toDouble() / MILLION, POSTFIX_MILLION)
            }
            else -> NOT_AVAILABLE
        }
    }

    private fun getFormattedText(dividedValue: Double, postFix: String): String {
        return "${String.format("%.1f", dividedValue)}$postFix"
    }
}