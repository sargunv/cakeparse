package me.sargunvohra.ktparse.parser

import me.sargunvohra.ktparse.lexer.Token

data class Result<out A>(
        val value: A,
        val remainder: Iterable<Token>
)