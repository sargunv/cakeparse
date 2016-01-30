package me.sargunvohra.lib.cakeparse.parser

import me.sargunvohra.lib.cakeparse.exception.UnexpectedEofException
import me.sargunvohra.lib.cakeparse.exception.UnexpectedTokenException
import me.sargunvohra.lib.cakeparse.lexer.Token
import me.sargunvohra.lib.cakeparse.lexer.TokenInstance
import me.sargunvohra.lib.common.CachedSequence

/**
 * A parser that parses a token.
 *
 * This parser is the most basic parser. It simply looks for one specific type of token. Other parsers combine instances
 * of this parser to generate complex parsing rules.
 *
 * @property targetType the type of token to target.
 */
class TokenParser(val targetType: Token) : Parser<TokenInstance> {

    /**
     * Check the first non-ignorable token in the input. If it is of type [targetType], then return a result containing the token.
     * Otherwise, throw an exception.
     *
     * @param input the input to parse.
     *
     * @throws UnexpectedTokenException when the type of the first token in the [input] is not equal to [targetType].
     * @throws UnexpectedEofException when the [input] is empty or contains only ignorable tokens.
     */
    override fun eat(input: CachedSequence<TokenInstance>): Result<TokenInstance> = input.firstOrNull()?.let { token ->
        when {
            token.type == targetType -> Result(token, input.drop(1))
            token.type.ignore -> this.eat(input.drop(1))
            else -> throw UnexpectedTokenException(targetType, token)
        }
    } ?: throw UnexpectedEofException(targetType)
}