package me.sargunvohra.lib.kake.api

import me.sargunvohra.lib.kake.exception.UnexpectedTokenException
import me.sargunvohra.lib.kake.lexer.Token
import me.sargunvohra.lib.kake.parser.IParser
import me.sargunvohra.lib.kake.parser.Result

fun <A> Iterable<Token>.parseToGoal(parser: IParser<A>) = parser(this)

fun <A> Iterable<Token>.parseToEnd(parser: IParser<A>): Result<A> {
    val result = this.parseToGoal(parser)
    result.remainder.firstOrNull()?.let {
        throw UnexpectedTokenException(null, it)
    }
    return result
}