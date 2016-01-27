package me.sargunvohra.ktparse

import me.sargunvohra.ktparse.api.*
import me.sargunvohra.ktparse.exception.LexerException
import me.sargunvohra.ktparse.exception.ParseException

// tokens
val a = token("A", "A")
val b = token("B", "B")
val tokens = setOf(a, b)

val unit = a and b map { it.first.raw + it.second.raw }
val list = zeroOrMore(unit)
val goal = list

fun main(vararg args: String) {
    val input = "ABABAB"

    try {
        println("Result: ${tokens.lexer().lex(input).parseAll(goal).value}")
    } catch(e: LexerException) {
        println("Scanning error: ${e.message}")
    } catch(e: ParseException) {
        println("Parsing error: ${e.message}")
    }
}