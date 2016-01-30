package me.sargunvohra.lib.cakeparse.exception

import me.sargunvohra.lib.cakeparse.lexer.ITokenType

class EndOfFileException(val expected: ITokenType) : ParseException(
        "Expected '${expected.name}' token, but reached end of file"
)