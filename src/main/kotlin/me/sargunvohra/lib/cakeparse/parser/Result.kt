package me.sargunvohra.lib.cakeparse.parser

import me.sargunvohra.lib.cakeparse.lexer.TokenInstance
import me.sargunvohra.lib.common.CachedSequence

/**
 * A result of a successful parse
 *
 * @param A The type of the result value
 *
 * @property value the object produced by the parse
 * @property remainder the remaining input after the parse
 */
data class Result<out A>(
        val value: A,
        val remainder: CachedSequence<TokenInstance>
)