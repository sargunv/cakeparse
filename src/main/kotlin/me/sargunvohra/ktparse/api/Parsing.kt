package me.sargunvohra.ktparse.api

import me.sargunvohra.ktparse.exception.UnexpectedTokenException
import me.sargunvohra.ktparse.lexer.Token
import me.sargunvohra.ktparse.parser.IParser
import me.sargunvohra.ktparse.parser.Result

fun <A> Iterable<Token>.parseToGoal(parser: IParser<A>) = parser(this)

fun <A> Iterable<Token>.parseToEnd(parser: IParser<A>): Result<A> {
    val result = this.parseToGoal(parser)
    result.remainder.firstOrNull()?.let {
        throw UnexpectedTokenException(null, it)
    }
    return result
}