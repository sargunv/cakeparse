package me.sargunvohra.lib.cakeparse.parser

import me.sargunvohra.lib.cakeparse.lexer.ITokenType
import me.sargunvohra.lib.cakeparse.lexer.Token
import me.sargunvohra.lib.common.CachedSequence

class ParsedTokenType(
        override val name: String,
        override val pattern: String,
        override val ignore: Boolean = false
): ITokenType, IParser<Token> {

    init {
        require(name.matches("[a-zA-Z0-9]+".toRegex())) { "name '$name' is not alphanumeric" }
    }

    val parser = TokenParser(this)
    override fun eat(input: CachedSequence<Token>) = parser.eat(input)
}