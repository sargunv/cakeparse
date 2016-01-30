package me.sargunvohra.lib.cakeparse.exception

/**
 * Parent class for all types of parsing errors. Does not include lexing errors. For those, see [LexerException].
 *
 * @param message the human readable message to describe the parsing error.
 */
open class ParseException(message: String) : Exception(message)