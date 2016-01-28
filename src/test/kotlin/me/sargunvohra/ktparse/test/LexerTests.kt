package me.sargunvohra.ktparse.test

import me.sargunvohra.ktparse.api.*
import me.sargunvohra.ktparse.example.CalculatorExample
import me.sargunvohra.ktparse.example.CalculatorExample.Tokens
import me.sargunvohra.ktparse.exception.LexerException
import me.sargunvohra.ktparse.lexer.Lexer
import me.sargunvohra.ktparse.lexer.Token
import org.jetbrains.spek.api.Spek
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class LexerTests : Spek() {
    init {

        given("a lexer with no token types") {
            on("instantiating the lexer") {
                it("should fail") {
                    assertFailsWith(IllegalArgumentException::class) { Lexer(emptySet()) }
                }
            }
        }

        given("a lexer with some token types") {
            val lexer = CalculatorExample.allTokens.lexer()

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
                    Token(Tokens.space, "   ", 0),
                    Token(Tokens.number, "1", 3),
                    Token(Tokens.plus, "+", 4),
                    Token(Tokens.number, "3", 5),
                    Token(Tokens.times, "*", 6),
                    Token(Tokens.lPar, "(", 7),
                    Token(Tokens.number, "400", 8),
                    Token(Tokens.space, "\t\t", 11),
                    Token(Tokens.over, "/", 13),
                    Token(Tokens.lPar, "(", 14),
                    Token(Tokens.minus, "-", 15),
                    Token(Tokens.number, "5", 16),
                    Token(Tokens.minus, "-", 17),
                    Token(Tokens.number, "3", 18),
                    Token(Tokens.rPar, ")", 19),
                    Token(Tokens.rPar, ")", 20),
                    Token(Tokens.space, " \n\r", 21)
            ))

            invalid("lexing an input with an invalid token in the middle", "   1+3*(4\ta\t/(5-3)) \n\r", 10, 'a')
            invalid("lexing an input with an invalid token at the start", "a   1+3*(4\t\t/(5-3)) \n\r", 0, 'a')
            invalid("lexing an input with an invalid token at the end", "   1+3*(4\t\t/(5-3)) \n\ra", 21, 'a')
        }
    }
}