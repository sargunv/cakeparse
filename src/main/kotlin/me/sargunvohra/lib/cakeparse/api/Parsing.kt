package me.sargunvohra.lib.cakeparse.api

import me.sargunvohra.lib.cakeparse.exception.UnexpectedTokenException
import me.sargunvohra.lib.cakeparse.lexer.Token
import me.sargunvohra.lib.cakeparse.parser.IParser
import me.sargunvohra.lib.cakeparse.parser.Result

fun <A> Iterable<Token>.parseToGoal(parser: IParser<A>) = parser(this)

fun <A> Iterable<Token>.parseToEnd(parser: IParser<A>): Result<A> {
    val result = this.parseToGoal(parser)
    result.remainder.filter { !it.type.ignore }.firstOrNull()?.let {
        throw UnexpectedTokenException(null, it)
    }
    return result
}