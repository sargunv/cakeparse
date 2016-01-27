package me.sargunvohra.ktparse.example

import me.sargunvohra.ktparse.lexer.Lexer
import me.sargunvohra.ktparse.lexer.ITokenType

enum class CalculatorToken(override val pattern: String): ITokenType {
    NUMBER  ("[0-9]+"),
    PLUS    ("\\+"),
    MINUS   ("\\-"),
    TIMES   ("\\*"),
    OVER    ("\\/"),
    LPAR    ("\\("),
    RPAR    ("\\)"),
    NEWLINE ("\n"),
    SPACE   ("[ \t\r]+")
}

object CalculatorLexer : Lexer(CalculatorToken.values().toSet())