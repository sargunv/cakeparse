package me.sargunvohra.lib.cakeparse.test

import cucumber.api.DataTable
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import me.sargunvohra.lib.cakeparse.api.lexer
import me.sargunvohra.lib.cakeparse.example.CalculatorExample
import me.sargunvohra.lib.cakeparse.exception.LexerException
import me.sargunvohra.lib.cakeparse.lexer.Lexer
import me.sargunvohra.lib.cakeparse.lexer.Token
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull

class LexingStepDef() {

    lateinit var lexer: Lexer
    lateinit var result: Sequence<Token>

    @Given("^a lexer for calculator tokens$")
    fun givenCalculatorTokenLexer() {
        lexer = CalculatorExample.allTokens.lexer()
    }

    @When("^it lexes the input \"([^\"]*)\"$")
    fun itLexesInput(input: String) {
        result = lexer.lex(input)
    }

    @Then("^the result is an empty list$")
    fun resultIsEmpty() {
        assertNull(result.firstOrNull())
    }

    @Then("^lexing fails with character '(.)' at position (\\d)$")
    fun lexingFailed(char: Char, pos: Int) {
        val e: LexerException = assertFailsWith(LexerException::class) {
            result.forEach {  }
        }
        assertEquals(char, e.char)
        assertEquals(pos, e.position)
    }

    @Then("^the token types, raw values, and positions are:$")
    fun tokensAre(table: DataTable) {
        val rows = table.gherkinRows
        result.asIterable().zip(rows).forEach {
            val (tok, row) = it
            assertEquals(tok.type.name, row.cells[0], "token type")
            assertEquals(tok.raw, row.cells[1], "token raw value")
            assertEquals(tok.position, row.cells[2].toInt(), "token position")
        }
    }
}