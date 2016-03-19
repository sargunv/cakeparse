package me.sargunvohra.lib.cakeparse

import me.sargunvohra.lib.cakeparse.api.*
import org.testng.annotations.Test
import kotlin.test.assertEquals

class ParsingTest {

    fun parse(input: String) = Calc.allTokens.lexer().lex(input).parseToEnd(Calc.Rules.expr).value

    fun parseZeroPlus(input: String) = Calc.allTokens.lexer().lex(input).parseToEnd(zeroOrMore(Calc.Rules.expr before Calc.Tokens.newline)).value

    fun parseOnePlus(input: String) = Calc.allTokens.lexer().lex(input).parseToEnd(oneOrMore(Calc.Rules.expr before Calc.Tokens.newline)).value

    @Test
    fun simple() = assertEquals(2, parse("1+1"))

    @Test
    fun complex() = assertEquals(43, parse("(6*4)+5+7*4/2"))

    @Test
    fun spaces() = assertEquals(48, parse("  6 * 4     \t + \n \n ( 5  + 7) * 4 /  2  "))

    @Test
    fun zeroToZeroPlus() = assertEquals(emptyList(), parseZeroPlus("\n"))

    @Test
    fun oneToZeroPlus() = assertEquals(listOf(2), parseZeroPlus("1+1\n"))

    @Test
    fun manyToZeroPlus() = assertEquals(listOf(2, 128, -72), parseZeroPlus("1+1\n(5 + 123)\n3 * (6-12) * 4\n"))

    @Test
    fun oneToOnePlus() = assertEquals(listOf(2), parseOnePlus("1+1\n"))

    @Test
    fun manyToOnePlus() = assertEquals(listOf(2, 128, -72), parseOnePlus("1+1\n(5 + 123)\n3 * (6-12) * 4\n"))
}