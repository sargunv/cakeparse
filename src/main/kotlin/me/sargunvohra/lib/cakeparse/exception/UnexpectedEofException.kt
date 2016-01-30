package me.sargunvohra.lib.cakeparse.exception

import me.sargunvohra.lib.cakeparse.lexer.Token

class UnexpectedEofException(val expected: Token) : ParseException(
        "Expected '${expected.name}' token, but reached end of file"
)