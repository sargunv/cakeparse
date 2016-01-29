package me.sargunvohra.lib.cakeparse.parser

import me.sargunvohra.lib.cakeparse.lexer.Token
import me.sargunvohra.lib.common.CachedSequence

interface IParser<out A> {
    fun eat(input: CachedSequence<Token>): Result<A>
}