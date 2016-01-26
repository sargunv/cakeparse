package me.sargunvohra.ktparse.lexer

data class Token(
        val type: TokenType,
        val raw: String,
        val position: Int
)