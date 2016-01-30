package me.sargunvohra.lib.cakeparse.api

import me.sargunvohra.lib.cakeparse.exception.UnexpectedTokenException
import me.sargunvohra.lib.cakeparse.lexer.TokenInstance
import me.sargunvohra.lib.cakeparse.parser.Parser
import me.sargunvohra.lib.cakeparse.parser.Result
import me.sargunvohra.lib.common.cached

fun <A> Sequence<TokenInstance>.parseToGoal(parser: Parser<A>) = parser.eat(this.cached())

fun <A> Sequence<TokenInstance>.parseToEnd(parser: Parser<A>): Result<A> {
    val result = this.parseToGoal(parser)
    result.remainder.filter { !it.type.ignore }.firstOrNull()?.let {
        throw UnexpectedTokenException(null, it)
    }
    return result
}