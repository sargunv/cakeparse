package me.sargunvohra.lib.cakeparse.lexer

/**
 * A definition of a type of token
 *
 * @property name the alphanumeric name of this token
 * @property pattern the regular expression defining the format of this token
 * @property ignore whether this token can be ignored while parsing, for example: whitespace
 */
interface Token {
    val name: String
    val pattern: String
    val ignore: Boolean
}

