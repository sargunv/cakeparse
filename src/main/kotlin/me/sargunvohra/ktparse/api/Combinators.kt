package me.sargunvohra.ktparse.api

import me.sargunvohra.ktparse.exception.ParseException
import me.sargunvohra.ktparse.parser.IParser
import me.sargunvohra.ktparse.parser.Parser
import me.sargunvohra.ktparse.parser.Result

infix fun <A, B> IParser<A>.and(other: IParser<B>) = Parser { input ->
    val result1 = this(input)
    val result2 = other(result1.remainder)
    Result(Pair(result1.value, result2.value), result2.remainder)
}

infix fun <A> IParser<A>.or(other: IParser<A>) = Parser { input ->
    try {
        this(input)
    } catch(e: ParseException) {
        other(input)
    }
}

infix fun <A, B> IParser<A>.map(selector: (A) -> B) = Parser { input ->
    this(input).let { result -> Result(selector(result.value), result.remainder) }
}

infix fun <A, B> IParser<A>.then(other: IParser<B>) = this and other map { it.second }

infix fun <A, B> IParser<A>.before(other: IParser<B>) = this and other map { it.first }