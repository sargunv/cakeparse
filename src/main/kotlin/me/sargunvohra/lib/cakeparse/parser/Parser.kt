package me.sargunvohra.lib.cakeparse.parser

import me.sargunvohra.lib.cakeparse.lexer.Token

class Parser<out A>(val func: (Iterable<Token>) -> Result<A>): IParser<A> {
    override fun invoke(input: Iterable<Token>) = func(input)
}

