package me.sargunvohra.lib.cakeparse.api

import me.sargunvohra.lib.cakeparse.exception.ParseException
import me.sargunvohra.lib.cakeparse.parser.Parser
import me.sargunvohra.lib.cakeparse.parser.BaseParser
import me.sargunvohra.lib.cakeparse.parser.Result
import java.util.*

/**
 * Create a new parser by combining two parsers. The new parser tries to satisfy both this parser and the provided
 * parser. The result contains a pair of the result from both parsers.
 *
 * @param other the parser to satisfy immediately after this one.
 *
 * @return a new parser which looks for this *and* the [other]
 */
infix fun <A, B> Parser<A>.and(other: Parser<B>) = BaseParser { input ->
    val result1 = this.eat(input)
    val result2 = other.eat(result1.remainder)
    Result(Pair(result1.value, result2.value), result2.remainder)
}

/**
 * Create a new parser by combining two parsers. The new parser tries to satisfy this parser first, and if it fails,
 * it will try to satisfy the other parser. The result contains the result of the parser that succeeded.
 *
 * @param other the parser to satisfy if this one fails.
 *
 * @return a new parser which looks for this *or* the [other]
 */
infix fun <A> Parser<A>.or(other: Parser<A>) = BaseParser { input ->
    try {
        this.eat(input)
    } catch(e: ParseException) {
        other.eat(input)
    }
}

/**
 * Maps the result of this parser to a new result obtained by applying the given [transform] function.
 *
 * @param transform a function to convert the original result to the new one.
 *
 * @return a new parser which parses the same rule as this one, but modifies the resulting object.
 */
infix fun <A, B> Parser<A>.map(transform: (A) -> B) = BaseParser { input ->
    this.eat(input).let { result -> Result(transform(result.value), result.remainder) }
}

/**
 * Like [and], but only returns the result to the right.
 */
infix fun <A, B> Parser<A>.then(other: Parser<B>) = this and other map { it.second }

/**
 * Like [and], but only returns the result to the left.
 */
infix fun <A, B> Parser<A>.before(other: Parser<B>) = this and other map { it.first }

/**
 * Create a parser that tries to satisfy the provided parser at least [n] number of times.
 *
 * @param n the minimum number of times to repeat the target parser.
 * @param target the target parser to repeat.
 *
 * @return the new parser that parses a sequence of [target] at least [n] times.
 */
fun <A> atLeast(n: Int, target: Parser<A>): Parser<List<A>> = BaseParser { input ->
    var (list, remainder) = repeat(n, target).eat(input)
    list = LinkedList<A>(list)
    do {
        val (next, r) = optional(target).eat(remainder)
        if (next != null) list.add(next)
        remainder = r
    } while (next != null)
    Result(Collections.unmodifiableList(list), remainder)
}

/**
 * Create a parser that tries to satisfy the provided parser exactly [n] number of times.
 *
 * @param n the number of times to repeat the target parser
 * @param target the target parser to repeat
 *
 * @return the new parser that parses a sequence of [target] exactly [n] times
 */
fun <A> repeat(n: Int, target: Parser<A>): Parser<List<A>> = BaseParser { input ->
    val list = LinkedList<A>()
    var remainder = input
    repeat(n) {
        val next = target.eat(remainder)
        remainder = next.remainder
        list.add(next.value)
    }
    Result(Collections.unmodifiableList(list), remainder)
}