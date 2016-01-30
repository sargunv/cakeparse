package me.sargunvohra.lib.cakeparse.parser

import me.sargunvohra.lib.cakeparse.lexer.TokenInstance
import me.sargunvohra.lib.common.CachedSequence

/**
 * A parser that consumes tokens to produce a result.
 *
 * @param A the type of value returned as the result of a successful parse.
 */
interface Parser<out A> {

    /**
     * Parse the input and return a result with the parsed value and the remaining input. If the input can
     * not be parsed, then throw a ParseException.
     *
     * @param input the input to parse, obtained from a lexer or from another parser.
     */
    fun eat(input: CachedSequence<TokenInstance>): Result<A>
}