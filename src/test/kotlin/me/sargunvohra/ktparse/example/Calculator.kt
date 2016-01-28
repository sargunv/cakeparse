package me.sargunvohra.ktparse.example

import me.sargunvohra.ktparse.api.*

object CalculatorExample {
    object Tokens {
        val number = token("number", "[0-9]+")
        val plus = token("plus", "\\+")
        val minus = token("minus", "\\-")
        val times = token("times", "\\*")
        val over = token("over", "\\/")
        val lPar = token("lPar", "\\(")
        val rPar = token("rPar", "\\)")
        val newLine = token("newline", "\\n")
        val space = token("space", "[ \\t\\r]+")
    }

    val allTokens = setOf(
            Tokens.number,
            Tokens.plus,
            Tokens.minus,
            Tokens.times,
            Tokens.over,
            Tokens.lPar,
            Tokens.rPar,
            Tokens.newLine,
            Tokens.space
    )
}