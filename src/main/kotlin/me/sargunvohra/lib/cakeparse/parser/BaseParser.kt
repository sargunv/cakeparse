package me.sargunvohra.lib.cakeparse.parser

import me.sargunvohra.lib.cakeparse.lexer.TokenInstance
import me.sargunvohra.lib.common.CachedSequence

/**
 * A basic parser used to combine other parsers based on the rules in the [block].
 *
 * @param A the type of value returned as the result of a successful parse.
 *
 * @property block a function containing the rules defining how to parse this parser's target.
 */
class BaseParser<out A>(val block: (CachedSequence<TokenInstance>) -> Result<A>) : Parser<A> {

    /**
     * @see [Parser.eat]
     */
    override fun eat(input: CachedSequence<TokenInstance>) = block(input)
}

