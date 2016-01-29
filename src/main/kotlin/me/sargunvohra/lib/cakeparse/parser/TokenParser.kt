package me.sargunvohra.lib.cakeparse.parser

import me.sargunvohra.lib.cakeparse.exception.EndOfFileException
import me.sargunvohra.lib.cakeparse.exception.UnexpectedTokenException
import me.sargunvohra.lib.cakeparse.lexer.ITokenType
import me.sargunvohra.lib.cakeparse.lexer.Token
import me.sargunvohra.lib.common.CachedSequence

class TokenParser(
        val type: ITokenType
): IParser<Token> {
    override fun eat(input: CachedSequence<Token>): Result<Token> = input.firstOrNull()?.let { token ->
        when {
            token.type == type -> Result(token, input.drop(1))
            token.type.ignore -> this.eat(input.drop(1))
            else -> throw UnexpectedTokenException(type, token)
        }
    } ?: throw EndOfFileException(type)
}