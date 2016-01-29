package me.sargunvohra.lib.cakeparse.parser

import me.sargunvohra.lib.cakeparse.lexer.Token
import me.sargunvohra.lib.common.CachedSequence

class Parser<out A>(val func: (CachedSequence<Token>) -> Result<A>): IParser<A> {
    override fun eat(input: CachedSequence<Token>) = func(input)
}

