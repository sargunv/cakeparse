package me.sargunvohra.lib.cakeparse.api

import me.sargunvohra.lib.cakeparse.lexer.BaseTokenType
import me.sargunvohra.lib.cakeparse.lexer.ITokenType
import me.sargunvohra.lib.cakeparse.lexer.Lexer

fun token(name: String, pattern: String) = BaseTokenType(name, pattern)

fun Set<ITokenType>.lexer() = Lexer(this)