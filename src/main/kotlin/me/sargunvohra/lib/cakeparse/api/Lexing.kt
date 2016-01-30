package me.sargunvohra.lib.cakeparse.api

import me.sargunvohra.lib.cakeparse.lexer.Token
import me.sargunvohra.lib.cakeparse.lexer.Lexer
import me.sargunvohra.lib.cakeparse.parser.ParsableToken

/**
 * Define a parsable token with the provided name and pattern.
 *
 * @param name the alphanumeric name of this token.
 * @param pattern the regular expression defining the format of this token.
 * @param ignore whether this token can be ignored while parsing, for example: whitespace.
 */
fun token(name: String, pattern: String, ignore: Boolean = false) = ParsableToken(name, pattern, ignore)

/**
 * Create a lexer configured to produce this set of tokens.
 */
fun Set<Token>.lexer() = Lexer(this)