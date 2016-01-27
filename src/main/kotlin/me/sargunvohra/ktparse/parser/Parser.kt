package me.sargunvohra.ktparse.parser

import me.sargunvohra.ktparse.lexer.Token

class Parser<out A>(val func: (Iterable<Token>) -> Result<A>): IParser<A> {
    override fun invoke(input: Iterable<Token>) = func(input)
}

