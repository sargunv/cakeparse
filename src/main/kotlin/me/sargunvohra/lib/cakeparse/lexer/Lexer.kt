package me.sargunvohra.lib.cakeparse.lexer

import me.sargunvohra.lib.cakeparse.exception.LexerException
import java.io.InputStream
import java.io.InputStreamReader
import java.io.StringReader
import java.util.*

open class Lexer(tokens: Set<ITokenType>) {

    private val patterns = tokens.toMap {
        it to it.pattern.toRegex().toPattern()
    }

    init {
        require(tokens.isNotEmpty()) { "Must provide at least one token" }
    }

    fun lex(input: String) = lex(StringReader(input))

    fun lex(input: InputStream) = lex(InputStreamReader(input))

    fun lex(input: Readable) = object : Sequence<Token> {

        override fun iterator(): Iterator<Token> {

            return object : Iterator<Token> {

                val scanner = Scanner(input).useDelimiter("")
                var currentPosition = 0
                var currentRow = 1
                var currentCol = 1

                override fun hasNext() = scanner.hasNext()

                override fun next(): Token {
                    for ((type, pattern) in patterns) {
                        try {
                            scanner.skip(pattern)
                        } catch (e: NoSuchElementException) {
                            continue
                        }
                        val match = scanner.match().group()

                        val result = Token(type, match, currentPosition, currentRow, currentCol)

                        currentPosition += match.length

                        currentRow += match.count { it == '\n' }

                        currentCol += match.length
                        val lastIndex = match.lastIndexOf('\n')
                        if (lastIndex >= 0)
                            currentCol = match.length - lastIndex

                        return result
                    }
                    throw LexerException(currentPosition, scanner.next(".")[0])
                }
            }
        }
    }.constrainOnce()
}