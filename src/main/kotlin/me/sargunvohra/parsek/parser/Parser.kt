package me.sargunvohra.parsek.parser

import me.sargunvohra.parsek.lexer.Token

class Parser<out A>(val func: (Iterable<Token>) -> Result<A>): IParser<A> {
    override fun invoke(input: Iterable<Token>) = func(input)
}

