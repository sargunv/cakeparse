package me.sargunvohra.ktparse.lexer

import me.sargunvohra.ktparse.exception.LexerException
import java.util.regex.Pattern

open class Lexer(val tokens: Set<ITokenType>) {

    private val pattern: Pattern

    init {
        if (tokens.isEmpty())
            throw IllegalArgumentException("Must provide at least one token")

        pattern = Pattern.compile(
                tokens.joinToString(separator = "|") {
                    val name = it.name
                    val pattern = it.pattern
                    "(?<$name>$pattern)"
                }
        );
    }

    fun lex(input: CharSequence) = object: Sequence<Token> {

        override fun iterator(): Iterator<Token> {
            val matcher = pattern.matcher(input)

            return object: Iterator<Token> {

                override fun hasNext(): Boolean {
                    return matcher.lookingAt() || !matcher.hitEnd()
                }

                override fun next(): Token {
                    val pos = matcher.regionStart()
                    if (matcher.lookingAt()) {
                        tokens.forEach { type ->
                            matcher.group(type.name)?.let { str ->
                                matcher.region(pos + str.length, matcher.regionEnd())
                                return Token(type, str, pos)
                            }
                        }
                        throw IllegalStateException()
                    }
                    throw LexerException(pos, input[pos])
                }
            }
        }
    }
}