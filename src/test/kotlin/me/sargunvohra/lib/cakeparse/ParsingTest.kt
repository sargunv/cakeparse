package me.sargunvohra.lib.cakeparse

import me.sargunvohra.lib.cakeparse.api.*
import org.testng.annotations.Test
import kotlin.test.assertEquals

class ParsingTest {

    private fun lex(input: String) = Calc.allTokens.lexer().lex(input)

    private fun parse(input: String) = lex(input).parseToEnd(Calc.Rules.expr).value

    private fun parseZeroPlus(input: String) = lex(input).parseToEnd(zeroOrMore(Calc.Rules.expr before Calc.Tokens.newline)).value

    private fun parseOnePlus(input: String) = lex(input).parseToEnd(oneOrMore(Calc.Rules.expr before Calc.Tokens.newline)).value

    private fun parseRepeat(n: Int, input: String) = lex(input).parseToEnd(repeat(n, Calc.Rules.expr before Calc.Tokens.newline)).value

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

    @Test
    fun repeat() = assertEquals(listOf(2, 128, -72), parseRepeat(3, "1+1\n(5 + 123)\n3 * (6-12) * 4\n"))
}