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
    val parser = TokenParser(this)
    override fun invoke(input: Sequence<Token>) = parser(input)
}