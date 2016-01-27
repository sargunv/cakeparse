package me.sargunvohra.ktparse.api

import me.sargunvohra.ktparse.exception.UnexpectedTokenException
import me.sargunvohra.ktparse.lexer.Token
import me.sargunvohra.ktparse.parser.IParser
import me.sargunvohra.ktparse.parser.Result

fun <A> Iterable<Token>.parseSome(parser: IParser<A>) = parser(this)

fun <A> Iterable<Token>.parseAll(parser: IParser<A>): Result<A> {
    val result = this.parseSome(parser)
    result.remainder.firstOrNull()?.let {
        throw UnexpectedTokenException(null, it)
    }
    return result
}