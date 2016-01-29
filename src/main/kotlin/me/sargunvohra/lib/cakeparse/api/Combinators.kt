package me.sargunvohra.lib.cakeparse.api

import me.sargunvohra.lib.cakeparse.exception.ParseException
import me.sargunvohra.lib.cakeparse.parser.IParser
import me.sargunvohra.lib.cakeparse.parser.Parser
import me.sargunvohra.lib.cakeparse.parser.Result

infix fun <A, B> IParser<A>.and(other: IParser<B>) = Parser { input ->
    val result1 = this.eat(input)
    val result2 = other.eat(result1.remainder)
    Result(Pair(result1.value, result2.value), result2.remainder)
}

infix fun <A> IParser<A>.or(other: IParser<A>) = Parser { input ->
    try {
        this.eat(input)
    } catch(e: ParseException) {
        other.eat(input)
    }
}

infix fun <A, B> IParser<A>.map(selector: (A) -> B) = Parser { input ->
    this.eat(input).let { result -> Result(selector(result.value), result.remainder) }
}

infix fun <A, B> IParser<A>.then(other: IParser<B>) = this and other map { it.second }

infix fun <A, B> IParser<A>.before(other: IParser<B>) = this and other map { it.first }