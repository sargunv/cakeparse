package me.sargunvohra.lib.kake.parser

import me.sargunvohra.lib.kake.lexer.Token

class Parser<out A>(val func: (Iterable<Token>) -> Result<A>): IParser<A> {
    override fun invoke(input: Iterable<Token>) = func(input)
}

