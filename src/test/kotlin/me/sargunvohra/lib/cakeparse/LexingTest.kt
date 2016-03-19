package me.sargunvohra.lib.cakeparse

import me.sargunvohra.lib.cakeparse.api.lexer
import me.sargunvohra.lib.cakeparse.exception.LexerException
import org.testng.annotations.Test
import java.io.ByteArrayInputStream
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class LexingTest {

    fun lex(input: String) = Calc.allTokens.lexer().lex(input).toList()

    fun lexStream(input: String) = Calc.allTokens.lexer().lex(ByteArrayInputStream(input.toByteArray())).toList()

    @Test
    fun empty() = assert(lex("").isEmpty())

    @Test
    fun invalidMiddle() {
        val e = assertFailsWith(LexerException::class) { lex("1+2+a+4+5") }
        assertEquals('a', e.char)
        assertEquals(4, e.pos)
        assertEquals(1, e.row)
        assertEquals(5, e.col)
    }

    @Test
    fun invalidStart() {
        val e = assertFailsWith(LexerException::class) { lex("b+2+3+4+5") }
        assertEquals('b', e.char)
        assertEquals(0, e.pos)
        assertEquals(1, e.row)
        assertEquals(1, e.col)
    }

    @Test
    fun invalidEnd() {
        val e = assertFailsWith(LexerException::class) { lex("1+2+3+4+c") }
        assertEquals('c', e.char)
        assertEquals(8, e.pos)
        assertEquals(1, e.row)
        assertEquals(9, e.col)
    }

    @Test
    fun complex() {
        val expected = with(Calc.Tokens) {
            listOf(
                    Triple(number, "1", 0),
                    Triple(plus, "+", 1),
                    Triple(number, "3", 2),
                    Triple(times, "*", 3),
                    Triple(lPar, "(", 4),
                    Triple(number, "400", 5),
                    Triple(over, "/", 8),
                    Triple(lPar, "(", 9),
                    Triple(number, "5", 10),
                    Triple(minus, "-", 11),
                    Triple(number, "3", 12),
                    Triple(rPar, ")", 13),
                    Triple(rPar, ")", 14)
            )
        }
        val got = lex("1+3*(400/(5-3))").map {
            Triple(it.type, it.raw, it.pos)
        }
        assertEquals(expected, got)
    }

    @Test
    fun complexStream() {
        val expected = with(Calc.Tokens) {
            listOf(
                    Triple(number, "1", 0),
                    Triple(plus, "+", 1),
                    Triple(number, "3", 2),
                    Triple(times, "*", 3),
                    Triple(lPar, "(", 4),
                    Triple(number, "400", 5),
                    Triple(over, "/", 8),
                    Triple(lPar, "(", 9),
                    Triple(number, "5", 10),
                    Triple(minus, "-", 11),
                    Triple(number, "3", 12),
                    Triple(rPar, ")", 13),
                    Triple(rPar, ")", 14)
            )
        }
        val got = lexStream("1+3*(400/(5-3))").map {
            Triple(it.type, it.raw, it.pos)
        }
        assertEquals(expected, got)
    }

    @Test
    fun multiline() {
        val expected = listOf(
                1 to 1,
                1 to 2,
                1 to 3,
                1 to 4,
                2 to 1,
                2 to 2,
                3 to 1,
                4 to 1,
                4 to 2,
                5 to 1,
                5 to 2,
                5 to 3,
                6 to 1,
                6 to 2,
                6 to 3,
                6 to 4
        )
        val got = lex("1+-\n+\n\n2\n()\n4*/\n").map {
            it.row to it.col
        }
        assertEquals(expected, got)
    }
}