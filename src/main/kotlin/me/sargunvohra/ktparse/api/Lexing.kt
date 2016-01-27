package me.sargunvohra.ktparse.api

import me.sargunvohra.ktparse.lexer.BaseTokenType
import me.sargunvohra.ktparse.lexer.ITokenType
import me.sargunvohra.ktparse.lexer.Lexer

fun token(name: String, pattern: String) = BaseTokenType(name, pattern)

fun Set<ITokenType>.lexer() = Lexer(this)