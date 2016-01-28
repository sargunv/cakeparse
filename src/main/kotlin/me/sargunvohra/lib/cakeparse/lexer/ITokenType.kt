package me.sargunvohra.lib.cakeparse.lexer

import me.sargunvohra.lib.cakeparse.parser.IParser
import me.sargunvohra.lib.cakeparse.parser.TokenParser

interface ITokenType {
    val name: String
    val pattern: String
    val ignore: Boolean
}

class BaseTokenType(
        override val name: String,
        override val pattern: String,
        override val ignore: Boolean = false
): ITokenType, IParser<Token> {

    init {
        require(name.matches("[a-zA-Z0-9]+".toRegex())) { "name '$name' is not alphanumeric" }
    }

    val parser = TokenParser(this)
    override fun invoke(input: Iterable<Token>) = parser(input)
}