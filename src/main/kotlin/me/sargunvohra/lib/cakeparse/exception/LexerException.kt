package me.sargunvohra.lib.cakeparse.exception

/**
 * Thrown when the lexer encounter a character that can not be parsed into any token.
 *
 * @property pos the overall position of the character in the input.
 * @property row the line number of the character in the input.
 * @property col the column number of the character in the input.
 * @property char the character that caused the problem.
 */
class LexerException(
        val pos: Int,
        val row: Int,
        val col: Int,
        val char: Char
) : Exception("Can't tokenize character '$char' at row $row, col $col")