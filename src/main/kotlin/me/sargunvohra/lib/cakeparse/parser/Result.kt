package me.sargunvohra.lib.cakeparse.parser

import me.sargunvohra.lib.cakeparse.lexer.Token

data class Result<out A>(
        val value: A,
        val remainder: Iterable<Token>
)