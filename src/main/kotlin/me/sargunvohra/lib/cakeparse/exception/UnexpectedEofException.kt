package me.sargunvohra.lib.cakeparse.exception

import me.sargunvohra.lib.cakeparse.lexer.Token

/**
 * Thrown when a parser encounters the end of the input when a token was expected.
 *
 * @param expected the token that was expected.
 */
class UnexpectedEofException(val expected: Token) : ParseException(
        "Expected '${expected.name}' token, but reached end of file"
)