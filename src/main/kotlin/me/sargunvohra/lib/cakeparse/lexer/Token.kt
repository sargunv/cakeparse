package me.sargunvohra.lib.cakeparse.lexer

interface Token {
    val name: String
    val pattern: String
    val ignore: Boolean
}

