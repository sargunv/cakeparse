package me.sargunvohra.lib.kake.api

import me.sargunvohra.lib.kake.lexer.BaseTokenType
import me.sargunvohra.lib.kake.lexer.ITokenType
import me.sargunvohra.lib.kake.lexer.Lexer

fun token(name: String, pattern: String) = BaseTokenType(name, pattern)

fun Set<ITokenType>.lexer() = Lexer(this)