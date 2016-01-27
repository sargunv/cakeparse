package me.sargunvohra.ktparse.exception

import me.sargunvohra.ktparse.lexer.ITokenType

class EndOfFileException(
        val expected: ITokenType
): ParseException("Expected '${expected.name}' token, but reached end of file")