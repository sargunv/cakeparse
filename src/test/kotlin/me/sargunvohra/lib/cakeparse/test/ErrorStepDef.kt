package me.sargunvohra.lib.cakeparse.test

import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import me.sargunvohra.lib.cakeparse.lexer.Token
import me.sargunvohra.lib.cakeparse.lexer.Lexer
import kotlin.test.assertFailsWith
import kotlin.test.fail

class ErrorStepDef {

    lateinit var tokens: Set<Token>
    var throwable: Throwable? = null

    @Given("^a set of no token types$")
    fun emptySetOfTokens() {
        tokens = emptySet()
    }

    @When("a lexer is instantiated from the set")
    fun instantiateLexer() {
        try {
            Lexer(tokens)
            throwable = null
        } catch (t: Throwable) {
            throwable = t
        }
    }

    @Then("the lexer throws an IllegalArgumentException")
    fun throwsIllegalArgumentException() {
        throwable?.let {
            assertFailsWith(IllegalArgumentException::class) { throw it }
        } ?: fail()
    }
}