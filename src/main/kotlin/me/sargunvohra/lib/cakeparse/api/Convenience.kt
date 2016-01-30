package me.sargunvohra.lib.cakeparse.api

import me.sargunvohra.lib.cakeparse.parser.Parser
import me.sargunvohra.lib.cakeparse.parser.BaseParser
import me.sargunvohra.lib.cakeparse.parser.Result

fun <A> ref(parser: () -> Parser<A>) = BaseParser { input -> parser().eat(input) }

fun <A> empty(): BaseParser<A?> = BaseParser { input -> Result<A?>(null, input) }

fun <A> optional(target: Parser<A>) = target or empty<A>()

fun <A> atLeast(n: Int, target: Parser<A>): Parser<List<A>> = when (n) {
    0 -> optional(target and ref({ atLeast(0, target) }) map { listOf(it.first) + it.second }) map { it.orEmpty() }
    else -> target and atLeast(n - 1, target) map { a -> listOf(a.first) + a.second.orEmpty() }
}

fun <A> zeroOrMore(target: Parser<A>) = atLeast(0, target)

fun <A> oneOrMore(target: Parser<A>) = atLeast(1, target)