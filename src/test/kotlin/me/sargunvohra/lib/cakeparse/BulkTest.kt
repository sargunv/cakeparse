package me.sargunvohra.lib.cakeparse

import me.sargunvohra.lib.cakeparse.api.*
import org.testng.annotations.Test
import kotlin.test.assertEquals

class BulkTest {

    private fun bulkTest(count: Int) {
        val number = token("number", "[0-9]+")
        val space = token("space", "\\s+")
        val goal = oneOrMore(number map { it.raw } before optional(space))
        val input = "557196 56123\n".repeat(count)
        val result = setOf(number, space).lexer().lex(input).parseToEnd(goal).value
        val expected = input.trim().split(" ", "\n")
        assertEquals(expected, result)
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
    fun bulkTest500() {
        bulkTest(500)
    }

    @Test
    fun bulkTest1000() {
        bulkTest(1000)
    }

    @Test
    fun bulkTest5000() {
        bulkTest(5000)
    }

}