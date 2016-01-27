package me.sargunvohra.ktparse

import me.sargunvohra.ktparse.exception.LexerException
import me.sargunvohra.ktparse.exception.ParseException
import me.sargunvohra.ktparse.lexer.BaseTokenType
import me.sargunvohra.ktparse.lexer.Lexer
import me.sargunvohra.ktparse.parser.IParser
import me.sargunvohra.ktparse.parser.api.*

// tokens
val lParen = BaseTokenType("LPAR", "\\(")
val rParen = BaseTokenType("RPAR", "\\)")
val tokens = setOf(lParen, rParen)

// reflexive references for recursive grammar
val pairR: IParser<Int> = ref({pair})

// parser grammar
val pair = optional(lParen then pairR before rParen) map { it?.inc() ?: 0 }

fun main(vararg args: String) {
    val input = "()"

    try {
        println("Depth: ${Lexer(tokens).lex(input).parseAll(pair).value}")
    } catch(e: ParseException) {
        println("Parsing error: ${e.message}")
    } catch(e: LexerException) {
        println("Scanning error: ${e.message}")
    }
}