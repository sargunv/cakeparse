package me.sargunvohra.parsek.test

import me.sargunvohra.parsek.api.lexer
import me.sargunvohra.parsek.api.parseToEnd
import me.sargunvohra.parsek.example.CalculatorExample
import org.jetbrains.spek.api.Spek
import kotlin.test.assertEquals

class ParserTests : Spek() {
    init {
        given("a parser for a calculator grammar") {
            val lexer = CalculatorExample.allTokens.lexer()
            val goal = CalculatorExample.Rules.expr

            fun valid(input: String, expected: Int) {
                on("parsing the valid input '$input'") {
                    val result = lexer.lex(input).parseToEnd(goal)
                    it("should return '$expected'") {
                        assertEquals(expected, result.value)
                    }
                }
            }

            valid("100", 100)
            valid("100+50", 150)
            valid("100-50", 50)
            valid("100*10", 1000)
            valid("100/2", 50)
            valid("(6*4)+5+7*4/2", 43)
            valid("6*4+(5+7)*4/2", 48)
        }
    }
}