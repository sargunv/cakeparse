package me.sargunvohra.parsek.exception

import me.sargunvohra.parsek.lexer.ITokenType

class EndOfFileException(
        val expected: ITokenType
): ParseException("Expected '${expected.name}' token, but reached end of file")