package me.sargunvohra.lib.kake.parser

import me.sargunvohra.lib.kake.exception.EndOfFileException
import me.sargunvohra.lib.kake.exception.UnexpectedTokenException
import me.sargunvohra.lib.kake.lexer.ITokenType
import me.sargunvohra.lib.kake.lexer.Token

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