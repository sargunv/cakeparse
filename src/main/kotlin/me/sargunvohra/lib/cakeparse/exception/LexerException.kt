package me.sargunvohra.lib.cakeparse.exception

class LexerException(
        val pos: Int,
        val row: Int,
        val col: Int,
        val char: Char
) : Exception("Can't tokenize character '$char' at index $pos")