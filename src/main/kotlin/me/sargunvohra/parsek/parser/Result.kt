package me.sargunvohra.parsek.parser

import me.sargunvohra.parsek.lexer.Token

data class Result<out A>(
        val value: A,
        val remainder: Iterable<Token>
)