package me.sargunvohra.parsek.exception

import me.sargunvohra.parsek.lexer.ITokenType
import me.sargunvohra.parsek.lexer.Token

class UnexpectedTokenException(
        val expected: ITokenType?,
        val got: Token
): ParseException(
        "Expected ${expected?.let {"'${expected.name}' token"} ?: "end of file"},"
                + " but got '${got.type.name}' at position ${got.position}"
)