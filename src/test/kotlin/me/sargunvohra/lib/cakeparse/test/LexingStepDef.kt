package me.sargunvohra.lib.cakeparse.test

import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import me.sargunvohra.lib.cakeparse.api.lexer
import me.sargunvohra.lib.cakeparse.example.CalculatorExample
import me.sargunvohra.lib.cakeparse.exception.LexerException
import me.sargunvohra.lib.cakeparse.lexer.Lexer
import me.sargunvohra.lib.cakeparse.lexer.Token
import org.junit.Assert.*
import kotlin.test.assertFailsWith

class LexingStepDef() {

    lateinit var lexer: Lexer
    lateinit var result: Iterable<Token>

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

    @Then("^the tokens are:$")
    fun tokensAre(names: List<String>) {
        assertEquals(names, result.map { it.type.name })
    }

    @Then("^the raw values are:$")
    fun rawValuesAre(values: List<String>) {
        assertEquals(values, result.map { it.raw })
    }

    @Then("^the positions are:$")
    fun positionsAre(positions: List<Int>) {
        assertEquals(positions, result.map { it.position })
    }
}