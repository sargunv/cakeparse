package me.sargunvohra.lib.cakeparse.lexer

/**
 * An instance of a token created when lexing.
 *
 * @see Lexer
 *
 * @property type the definition of this type of token.
 * @property raw the string value that matched this token's format.
 * @property pos the overall position in the original input this token starts at.
 * @property row the line number that this token starts at.
 * @property col the column number that this token starts at.
 */
data class TokenInstance(
        val type: Token,
        val raw: String,
        val pos: Int,
        val row: Int,
        val col: Int
)