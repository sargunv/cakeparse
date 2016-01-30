package me.sargunvohra.lib.cakeparse.lexer

data class TokenInstance(
        val type: Token,
        val raw: String,
        val position: Int,
        val row: Int,
        val col: Int
)