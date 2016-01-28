package me.sargunvohra.ktparse.lexer

import me.sargunvohra.ktparse.parser.IParser
import me.sargunvohra.ktparse.parser.TokenParser

interface ITokenType {
    val name: String
    val pattern: String
}

class BaseTokenType(
        override val name: String,
        override val pattern: String
): ITokenType, IParser<Token> {

    init {
        require(name.matches("[a-zA-Z0-9]+".toRegex())) { "name '$name' is not alphanumeric" }
    }

    val parser = TokenParser(this)
    override fun invoke(input: Iterable<Token>) = parser(input)
}