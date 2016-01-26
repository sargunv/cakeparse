package me.sargunvohra.ktparse.test

import me.sargunvohra.ktparse.example.CalculatorLexer
import me.sargunvohra.ktparse.example.CalculatorToken
import me.sargunvohra.ktparse.lexer.LexerException
import me.sargunvohra.ktparse.lexer.Token
import org.jetbrains.spek.api.Spek
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class LexerSpecs: Spek() {
    init {
        given("a lexer for calculator tokens") {
            val lexer = CalculatorLexer

            fun valid(desc: String, input: CharSequence, correct: List<Token>) {
                on(desc) {
                    val result = lexer.lex(input)
                    it("should accept all the input") {
                        result.forEach {  }
                    }
                    it("should present the appropriate tokens") {
                        assertTrue(result.toList() == correct)
                    }
                }
            }

            fun invalid(desc: String, input: CharSequence, pos: Int, char: Char) {
                on(desc) {
                    val result = lexer.lex(input)
                    var ex: LexerException? = null
                    it("should reject the input") {
                        ex = assertFailsWith(LexerException::class) { result.forEach { } }
                    }
                    it("should report the proper error position") {
                        kotlin.test.assertEquals(pos, ex?.position)
                    }
                    it("should report the proper error character") {
                        kotlin.test.assertEquals(char, ex?.char)
                    }
                }
            }

            valid("lexing an empty input", "", emptyList())
            valid("lexing a valid input", "   1+3*(400\t\t/(-5-3)) \n\r", listOf(
                    Token(CalculatorToken.SPACE, "   ", 0),
                    Token(CalculatorToken.NUMBER, "1", 3),
                    Token(CalculatorToken.PLUS, "+", 4),
                    Token(CalculatorToken.NUMBER, "3", 5),
                    Token(CalculatorToken.TIMES, "*", 6),
                    Token(CalculatorToken.LPAR, "(", 7),
                    Token(CalculatorToken.NUMBER, "400", 8),
                    Token(CalculatorToken.SPACE, "\t\t", 11),
                    Token(CalculatorToken.OVER, "/", 13),
                    Token(CalculatorToken.LPAR, "(", 14),
                    Token(CalculatorToken.MINUS, "-", 15),
                    Token(CalculatorToken.NUMBER, "5", 16),
                    Token(CalculatorToken.MINUS, "-", 17),
                    Token(CalculatorToken.NUMBER, "3", 18),
                    Token(CalculatorToken.RPAR, ")", 19),
                    Token(CalculatorToken.RPAR, ")", 20),
                    Token(CalculatorToken.SPACE, " ", 21),
                    Token(CalculatorToken.NEWLINE, "\n", 22),
                    Token(CalculatorToken.SPACE, "\r", 23)
            ))

            invalid("lexing an input with an invalid token in the middle", "   1+3*(4\ta\t/(5-3)) \n\r", 10, 'a')
            invalid("lexing an input with an invalid token at the start", "a   1+3*(4\t\t/(5-3)) \n\r", 0, 'a')
            invalid("lexing an input with an invalid token at the end", "   1+3*(4\t\t/(5-3)) \n\ra", 21, 'a')
        }
    }
}