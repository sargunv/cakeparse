package me.sargunvohra.lib.cakeparse.parser

import me.sargunvohra.lib.cakeparse.lexer.Token
import me.sargunvohra.lib.common.CachedSequence

data class Result<out A>(
        val value: A,
        val remainder: CachedSequence<Token>
)