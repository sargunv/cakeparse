package me.sargunvohra.lib.cakeparse.api

import me.sargunvohra.lib.cakeparse.lexer.ITokenType
import me.sargunvohra.lib.cakeparse.lexer.Lexer
import me.sargunvohra.lib.cakeparse.parser.ParsedTokenType

fun token(name: String, pattern: String, ignore: Boolean = false) = ParsedTokenType(name, pattern, ignore)

fun Set<ITokenType>.lexer() = Lexer(this)