package me.sargunvohra.ktparse.parser

import me.sargunvohra.ktparse.lexer.Token

class Parser<out A>(val func: (Sequence<Token>) -> Result<A>): IParser<A> {
    override fun invoke(input: Sequence<Token>) = func(input)
}

