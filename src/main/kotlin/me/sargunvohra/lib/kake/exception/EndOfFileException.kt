package me.sargunvohra.lib.kake.exception

import me.sargunvohra.lib.kake.lexer.ITokenType

class EndOfFileException(
        val expected: ITokenType
): ParseException("Expected '${expected.name}' token, but reached end of file")