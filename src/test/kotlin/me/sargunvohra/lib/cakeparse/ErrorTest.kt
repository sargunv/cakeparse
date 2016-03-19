package me.sargunvohra.lib.cakeparse

import me.sargunvohra.lib.cakeparse.api.lexer
import me.sargunvohra.lib.cakeparse.api.parseToEnd
import me.sargunvohra.lib.cakeparse.exception.UnexpectedEofException
import me.sargunvohra.lib.cakeparse.exception.UnexpectedTokenException
import me.sargunvohra.lib.cakeparse.lexer.Lexer
import org.testng.annotations.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ErrorTest {

    fun parse(input: String) = Calc.allTokens.lexer().lex(input).parseToEnd(Calc.Rules.expr).value

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
}