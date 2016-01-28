package me.sargunvohra.lib.kake.exception

import me.sargunvohra.lib.kake.lexer.ITokenType
import me.sargunvohra.lib.kake.lexer.Token

class UnexpectedTokenException(
        val expected: ITokenType?,
        val got: Token
): ParseException(
        "Expected ${expected?.let {"'${expected.name}' token"} ?: "end of file"},"
                + " but got '${got.type.name}' at position ${got.position}"
)