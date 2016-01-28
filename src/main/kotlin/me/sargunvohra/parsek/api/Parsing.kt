package me.sargunvohra.parsek.api

import me.sargunvohra.parsek.exception.UnexpectedTokenException
import me.sargunvohra.parsek.lexer.Token
import me.sargunvohra.parsek.parser.IParser
import me.sargunvohra.parsek.parser.Result

fun <A> Iterable<Token>.parseToGoal(parser: IParser<A>) = parser(this)

fun <A> Iterable<Token>.parseToEnd(parser: IParser<A>): Result<A> {
    val result = this.parseToGoal(parser)
    result.remainder.firstOrNull()?.let {
        throw UnexpectedTokenException(null, it)
    }
    return result
}