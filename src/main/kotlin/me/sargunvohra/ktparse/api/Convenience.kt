package me.sargunvohra.ktparse.api

import me.sargunvohra.ktparse.parser.IParser
import me.sargunvohra.ktparse.parser.Parser
import me.sargunvohra.ktparse.parser.Result

fun <A> ref(parser: ()-> IParser<A>) = Parser { input -> parser().invoke(input) }

fun <A> empty(): Parser<A?> = Parser { input -> Result<A?>(null, input) }

fun <A> optional(target: IParser<A>) = target or empty<A>()

fun <A> atLeast(n: Int, target: IParser<A>): IParser<List<A>> = when (n) {
    0 -> optional(target and ref({atLeast(0, target)}) map { listOf(it.first) + it.second }) map { it.orEmpty() }
    else -> target and atLeast(n-1, target) map { a -> listOf(a.first) + a.second.orEmpty() }
}

fun <A> zeroOrMore(target: IParser<A>) = atLeast(0, target)

fun <A> oneOrMore(target: IParser<A>) = atLeast(1, target)