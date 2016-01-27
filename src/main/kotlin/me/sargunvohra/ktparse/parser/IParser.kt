package me.sargunvohra.ktparse.parser

import me.sargunvohra.ktparse.lexer.Token

interface IParser<out A> {
    operator fun invoke(input: Sequence<Token>): Result<A>
}