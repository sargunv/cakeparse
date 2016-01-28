package me.sargunvohra.lib.cakeparse.parser

import me.sargunvohra.lib.cakeparse.lexer.Token

interface IParser<out A> {
    operator fun invoke(input: Iterable<Token>): Result<A>
}