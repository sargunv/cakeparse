package me.sargunvohra.ktparse.parser

import me.sargunvohra.ktparse.exception.EndOfFileException
import me.sargunvohra.ktparse.exception.UnexpectedTokenException
import me.sargunvohra.ktparse.lexer.Token
import me.sargunvohra.ktparse.lexer.ITokenType

class TokenParser(
        val type: ITokenType
): IParser<Token> {
    override fun invoke(input: Iterable<Token>) = input.firstOrNull()?.let { token ->
        when(token.type) {
            type -> Result(token, input.drop(1))
            else -> throw UnexpectedTokenException(type, token)
        }
    } ?: throw EndOfFileException(type)
}