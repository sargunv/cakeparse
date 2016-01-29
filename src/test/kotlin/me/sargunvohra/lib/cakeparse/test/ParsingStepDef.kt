package me.sargunvohra.lib.cakeparse.test

import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import me.sargunvohra.lib.cakeparse.api.lexer
import me.sargunvohra.lib.cakeparse.api.parseToEnd
import me.sargunvohra.lib.cakeparse.example.CalculatorExample
import me.sargunvohra.lib.cakeparse.lexer.Lexer
import me.sargunvohra.lib.cakeparse.lexer.Token
import me.sargunvohra.lib.cakeparse.parser.IParser
import me.sargunvohra.lib.cakeparse.parser.Result
import org.junit.Assert.*

class ParsingStepDef() {

    lateinit var lexer: Lexer
    lateinit var parser: IParser<Int>
    lateinit var result: Result<Int>

    @Given("^a parser for a calculator expression$")
    fun givenCalculatorExprGoal() {
        parser = CalculatorExample.Rules.expr
        lexer = CalculatorExample.allTokens.lexer()
    }

    @When("^it parses \"(.*)\"$")
    fun itParses(input: String) {
        result = lexer.lex(input).parseToEnd(parser)
    }

    @When("^it parses the multiline input:$")
    fun itParsesMultiline(input: String) = itParses(input)

    @Then("^the result is \"(\\d+)\"$")
    fun itReturns(value: Int) {
        assertEquals(value, result.value)
    }
}