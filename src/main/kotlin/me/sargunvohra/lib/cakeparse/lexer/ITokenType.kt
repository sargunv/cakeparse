package me.sargunvohra.lib.cakeparse.lexer

interface ITokenType {
    val name: String
    val pattern: String
    val ignore: Boolean
}

