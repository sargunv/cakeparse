package me.sargunvohra.parsek.parser

import me.sargunvohra.parsek.exception.EndOfFileException
import me.sargunvohra.parsek.exception.UnexpectedTokenException
import me.sargunvohra.parsek.lexer.ITokenType
import me.sargunvohra.parsek.lexer.Token

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