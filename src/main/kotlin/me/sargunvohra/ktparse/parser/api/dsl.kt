package me.sargunvohra.ktparse.parser.api

import me.sargunvohra.ktparse.exception.ParseException
import me.sargunvohra.ktparse.exception.UnexpectedTokenException
import me.sargunvohra.ktparse.lexer.Token
import me.sargunvohra.ktparse.parser.IParser
import me.sargunvohra.ktparse.parser.Parser
import me.sargunvohra.ktparse.parser.Result

fun <A> ref(parser: ()-> IParser<A>) = Parser { input ->
    parser().invoke(input)
}

fun <A> Iterable<Token>.parse(parser: IParser<A>) = parser(this)

fun <A> Iterable<Token>.parseAll(parser: IParser<A>): Result<A> {
    val result = parser(this)
    result.remainder.firstOrNull()?.let {
        throw UnexpectedTokenException(null, it)
    }
    return result
}

infix fun <A, B> IParser<A>.map(selector: (A) -> B) = Parser { input ->
    this(input).let { result -> Result(selector(result.value), result.remainder) }
}

infix fun <A, B> IParser<A>.and(other: IParser<B>) = Parser { input ->
    val result1 = this(input)
    val result2 = other(result1.remainder)
    Result(Pair(result1.value, result2.value), result2.remainder)
}

infix fun <A, B> IParser<A>.then(other: IParser<B>) = this and other map { it.second }

infix fun <A, B> IParser<A>.before(other: IParser<B>) = this and other map { it.first }

infix fun <A> IParser<A>.or(other: IParser<A>) = Parser { input ->
    try {
        this(input)
    } catch(e: ParseException) {
        other(input)
    }
}

fun <A> optional(target: IParser<A>) = Parser { input ->
    try {
        target(input)
    } catch (e: ParseException) {
        Result<A?>(null, input)
    }
}