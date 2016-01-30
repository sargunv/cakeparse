package me.sargunvohra.lib.cakeparse.parser

import me.sargunvohra.lib.cakeparse.lexer.Token
import me.sargunvohra.lib.cakeparse.lexer.TokenInstance
import me.sargunvohra.lib.common.CachedSequence

/**
 * A definition of a type of token that can be parsed.
 *
 * @property name the alphanumeric name of this token.
 * @property pattern the regular expression defining the format of this token.
 * @property ignore whether this token can be ignored while parsing, for example: whitespace.
 */
class ParsableToken(
        override val name: String,
        override val pattern: String,
        override val ignore: Boolean = false
) : Token, Parser<TokenInstance> {

    init {
        require(name.matches("[a-zA-Z0-9]+".toRegex())) { "name '$name' is not alphanumeric" }
    }

    private val parser = TokenParser(this)

    /**
     * @see [Parser.eat]
     */
    override fun eat(input: CachedSequence<TokenInstance>) = parser.eat(input)
}