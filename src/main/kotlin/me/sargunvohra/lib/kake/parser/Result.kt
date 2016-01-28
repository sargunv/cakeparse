package me.sargunvohra.lib.kake.parser

import me.sargunvohra.lib.kake.lexer.Token

data class Result<out A>(
        val value: A,
        val remainder: Iterable<Token>
)