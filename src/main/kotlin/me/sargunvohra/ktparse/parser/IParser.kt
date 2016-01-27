package me.sargunvohra.ktparse.parser

import me.sargunvohra.ktparse.lexer.Token

interface IParser<out A> {
    operator fun invoke(input: Iterable<Token>): Result<A>
}