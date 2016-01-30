package me.sargunvohra.lib.cakeparse.api

import me.sargunvohra.lib.cakeparse.exception.ParseException
import me.sargunvohra.lib.cakeparse.parser.Parser
import me.sargunvohra.lib.cakeparse.parser.BaseParser
import me.sargunvohra.lib.cakeparse.parser.Result

infix fun <A, B> Parser<A>.and(other: Parser<B>) = BaseParser { input ->
    val result1 = this.eat(input)
    val result2 = other.eat(result1.remainder)
    Result(Pair(result1.value, result2.value), result2.remainder)
}

infix fun <A> Parser<A>.or(other: Parser<A>) = BaseParser { input ->
    try {
        this.eat(input)
    } catch(e: ParseException) {
        other.eat(input)
    }
}

infix fun <A, B> Parser<A>.map(selector: (A) -> B) = BaseParser { input ->
    this.eat(input).let { result -> Result(selector(result.value), result.remainder) }
}

infix fun <A, B> Parser<A>.then(other: Parser<B>) = this and other map { it.second }

infix fun <A, B> Parser<A>.before(other: Parser<B>) = this and other map { it.first }