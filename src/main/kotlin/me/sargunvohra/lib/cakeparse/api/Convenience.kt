package me.sargunvohra.lib.cakeparse.api

import me.sargunvohra.lib.cakeparse.parser.IParser
import me.sargunvohra.lib.cakeparse.parser.Parser
import me.sargunvohra.lib.cakeparse.parser.Result

fun <A> ref(parser: () -> IParser<A>) = Parser { input -> parser().eat(input) }

fun <A> empty(): Parser<A?> = Parser { input -> Result<A?>(null, input) }

fun <A> optional(target: IParser<A>) = target or empty<A>()

fun <A> atLeast(n: Int, target: IParser<A>): IParser<List<A>> = when (n) {
    0 -> optional(target and ref({ atLeast(0, target) }) map { listOf(it.first) + it.second }) map { it.orEmpty() }
    else -> target and atLeast(n - 1, target) map { a -> listOf(a.first) + a.second.orEmpty() }
}

fun <A> zeroOrMore(target: IParser<A>) = atLeast(0, target)

fun <A> oneOrMore(target: IParser<A>) = atLeast(1, target)