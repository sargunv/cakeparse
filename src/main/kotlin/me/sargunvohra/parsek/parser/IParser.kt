package me.sargunvohra.parsek.parser

import me.sargunvohra.parsek.lexer.Token

interface IParser<out A> {
    operator fun invoke(input: Iterable<Token>): Result<A>
}