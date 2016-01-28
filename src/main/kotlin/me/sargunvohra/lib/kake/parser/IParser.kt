package me.sargunvohra.lib.kake.parser

import me.sargunvohra.lib.kake.lexer.Token

interface IParser<out A> {
    operator fun invoke(input: Iterable<Token>): Result<A>
}