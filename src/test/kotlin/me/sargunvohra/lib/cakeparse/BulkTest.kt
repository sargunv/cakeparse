package me.sargunvohra.lib.cakeparse

import me.sargunvohra.lib.cakeparse.api.*
import org.testng.annotations.Test
import kotlin.test.assertEquals

class BulkTest {

    private fun bulkTest(count: Int) {
        bulkTestMore(count)
        bulkTestExact(count)
    }

    private fun bulkTestMore(count: Int) {
        val number = token("number", "[0-9]+")
        val space = token("space", "\\s+")
        val goal = zeroOrMore(number map { it.raw } before optional(space))
        val input = "557196 56123\n".repeat(count)
        val result = setOf(number, space).lexer().lex(input).parseToEnd(goal).value
        val expected = if (count > 0) input.trim().split(" ", "\n") else emptyList()
        assertEquals(expected, result)
    }

    private fun bulkTestExact(count: Int) {
        val number = token("number", "[0-9]+")
        val space = token("space", "\\s+")
        val goal = repeat(count * 2, number map { it.raw } before optional(space))
        val input = "01234 56789\n".repeat(count)
        val result = setOf(number, space).lexer().lex(input).parseToEnd(goal).value
        val expected = if (count > 0) input.trim().split(" ", "\n") else emptyList()
        assertEquals(expected, result)
    }

    @Test
    fun bulkTest0() {
        bulkTest(0)
    }

    @Test
    fun bulkTest1() {
        bulkTest(1)
    }

    @Test
    fun bulkTest10() {
        bulkTest(10)
    }

    @Test
    fun bulkTest100() {
        bulkTest(100)
    }

    @Test
    fun bulkTest1000() {
        bulkTest(1000)
    }

}