package me.sargunvohra.lib.cakeparse.exception

import me.sargunvohra.lib.cakeparse.lexer.Token
import me.sargunvohra.lib.cakeparse.lexer.TokenInstance

/**
 * Thrown when a parser encounters a token that does not follow the expected syntax.
 *
 * @property expected a token that was expected, or null if end of file was expected.
 * @property got the incorrect token that was seen.
 */
class UnexpectedTokenException(
        val expected: Token?,
        val got: TokenInstance
) : ParseException(
        "Expected ${expected?.let { "'${expected.name}' token" } ?: "end of file"},"
                + " but got '${got.type.name}' at row ${got.row}, column ${got.col}"
)