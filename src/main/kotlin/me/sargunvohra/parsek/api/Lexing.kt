package me.sargunvohra.parsek.api

import me.sargunvohra.parsek.lexer.BaseTokenType
import me.sargunvohra.parsek.lexer.ITokenType
import me.sargunvohra.parsek.lexer.Lexer

fun token(name: String, pattern: String) = BaseTokenType(name, pattern)

fun Set<ITokenType>.lexer() = Lexer(this)