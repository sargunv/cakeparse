package me.sargunvohra.ktparse.lexer

class LexerException(
        val position: Int,
        val char: Char
): Exception("Can't tokenize character '$char' at index $position")