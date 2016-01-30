package me.sargunvohra.lib.cakeparse.api

import me.sargunvohra.lib.cakeparse.parser.Parser
import me.sargunvohra.lib.cakeparse.parser.BaseParser
import me.sargunvohra.lib.cakeparse.parser.Result

/**
 * Convenience function to create a reference to a parser for a recursive dependency.
 *
 * @param parser a function that just returns the target parser.
 *
 * @return a new parser that acts just like the provided parser.
 */
fun <A> ref(parser: () -> Parser<A>) = BaseParser { input -> parser().eat(input) }

/**
 * Create a parser that is always satisfied, so it is satisfied by consuming no input.
 *
 * @return a new parser that is always satisfied and consumes no input.
 */
fun <A> empty(): BaseParser<A?> = BaseParser { input -> Result<A?>(null, input) }

/**
 * Create a parser that tries to satisfy the provided parser, but still succeeds if the provided parser fails.
 *
 * @param target the target parser to try.
 *
 * @return a new parser that is always satisfied, but tries to parse the [target].
 */
fun <A> optional(target: Parser<A>) = target or empty<A>()

/**
 * Create a parser that tries to satisfy the provided parser at least [n] number of times.
 *
 * @param n the number of times to repeat the target parser.
 * @param target the target parser to repeat.
 *
 * @return the new parser that parses a sequence of [target] at least [n] times.
 */
fun <A> atLeast(n: Int, target: Parser<A>): Parser<List<A>> = when (n) {
    0 -> optional(target and ref({ atLeast(0, target) }) map { listOf(it.first) + it.second }) map { it.orEmpty() }
    else -> target and atLeast(n - 1, target) map { a -> listOf(a.first) + a.second.orEmpty() }
}

/**
 * Equivalent to [atLeast(0, target)][atLeast].
 */
fun <A> zeroOrMore(target: Parser<A>) = atLeast(0, target)

/**
 * Equivalent to [atLeast(1, target)][atLeast].
 */
fun <A> oneOrMore(target: Parser<A>) = atLeast(1, target)