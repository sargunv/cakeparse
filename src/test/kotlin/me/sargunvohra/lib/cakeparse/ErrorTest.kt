package me.sargunvohra.lib.cakeparse

import me.sargunvohra.lib.cakeparse.api.*
import me.sargunvohra.lib.cakeparse.exception.*
import me.sargunvohra.lib.cakeparse.lexer.Lexer
import org.testng.annotations.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ErrorTest {

    fun lex(input: String) = Calc.allTokens.lexer().lex(input)

    private fun parse(input: String) = lex(input).parseToEnd(Calc.Rules.expr).value

    @Test
    fun noTokens() = assertFailsWith<IllegalArgumentException> { Lexer(emptySet()) }

    @Test
    fun expectedEof() {
        val e = assertFailsWith<UnexpectedTokenException> { parse("1+1 1") }
        assertEquals(null, e.expected)
        assertEquals(Calc.Tokens.number, e.got.type)
    }

    @Test
    fun expectedOtherToken() {
        val e = assertFailsWith<UnexpectedTokenException> { parse("()") }
        assertEquals(Calc.Tokens.lPar, e.expected)
        assertEquals(Calc.Tokens.rPar, e.got.type)
    }

    @Test
    fun unexpectedEof() {
        val e = assertFailsWith<UnexpectedEofException> { parse("(") }
        assertEquals(Calc.Tokens.lPar, e.expected)
    }

    @Test
    fun repeatTooFew() {
        val e = assertFailsWith<UnexpectedEofException> {
            lex("1\n2\n3\n4\n").parseToEnd(repeat(5, Calc.Rules.expr  before Calc.Tokens.newline)).value
        }
        assertEquals(Calc.Tokens.lPar, e.expected)
    }

    @Test
    fun repeatTooMany() {
        val e = assertFailsWith<UnexpectedTokenException> {
            lex("1\n2\n3\n4\n5\n6\n").parseToEnd(repeat(5, Calc.Rules.expr  before Calc.Tokens.newline)).value
        }
        assertEquals(null, e.expected)
        assertEquals(Calc.Tokens.number, e.got.type)
    }
}