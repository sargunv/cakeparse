package me.sargunvohra.lib.cakeparse.example

import me.sargunvohra.lib.cakeparse.api.*
import me.sargunvohra.lib.cakeparse.parser.IParser

object CalculatorExample {
    object Tokens {
        val number = token("number", "[0-9]+")
        val plus = token("plus", "\\+")
        val minus = token("minus", "\\-")
        val times = token("times", "\\*")
        val over = token("over", "\\/")
        val lPar = token("lPar", "\\(")
        val rPar = token("rPar", "\\)")
        val space = token("space", "[ \\t\\r\\n]+", ignore = true)
    }

    object Rules {
        // convenience references for recursive rules
        val exprRef: IParser<Int> = ref { expr }
        val multExprRef: IParser<Int> = ref { multExpr }
        val addExprRef: IParser<Int> = ref { addExpr }

        // actual rules
        val parenExpr = Tokens.lPar then exprRef before Tokens.rPar

        val primExpr = Tokens.number map { it.raw.toInt() } or parenExpr

        val multExpr = primExpr and optional((Tokens.times or Tokens.over) and multExprRef) map { exp ->
            val (left, mult) = exp
            mult?.let {
                val (op, right) = it
                when (op.type) {
                    Tokens.times -> left * right
                    Tokens.over -> left / right
                    else -> throw IllegalStateException()
                }
            } ?: left
        }

        val addExpr = multExpr and optional((Tokens.plus or Tokens.minus) and addExprRef) map { exp ->
            val (left, mult) = exp
            mult?.let {
                val (op, right) = it
                when (op.type) {
                    Tokens.plus -> left + right
                    Tokens.minus -> left - right
                    else -> throw IllegalStateException()
                }
            } ?: left
        }

        val expr: IParser<Int> = addExpr
    }

    val allTokens = setOf(
            Tokens.number,
            Tokens.plus,
            Tokens.minus,
            Tokens.times,
            Tokens.over,
            Tokens.lPar,
            Tokens.rPar,
            Tokens.space
    )
}