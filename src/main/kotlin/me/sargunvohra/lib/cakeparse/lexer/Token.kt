package me.sargunvohra.lib.cakeparse.lexer

data class Token(
        val type: ITokenType,
        val raw: String,
        val position: Int,
        val row: Int,
        val col: Int
)