package me.sargunvohra.lib.cakeparse.api

import me.sargunvohra.lib.cakeparse.lexer.Token
import me.sargunvohra.lib.cakeparse.lexer.Lexer
import me.sargunvohra.lib.cakeparse.parser.ParsableToken

fun token(name: String, pattern: String, ignore: Boolean = false) = ParsableToken(name, pattern, ignore)

fun Set<Token>.lexer() = Lexer(this)