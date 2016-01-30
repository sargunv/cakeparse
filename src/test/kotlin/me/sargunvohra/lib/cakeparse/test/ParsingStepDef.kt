package me.sargunvohra.lib.cakeparse.test

import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import me.sargunvohra.lib.cakeparse.api.*
import me.sargunvohra.lib.cakeparse.example.CalculatorExample
import me.sargunvohra.lib.cakeparse.lexer.Lexer
import me.sargunvohra.lib.cakeparse.parser.IParser
import me.sargunvohra.lib.cakeparse.parser.Result
import kotlin.test.assertEquals

class ParsingStepDef() {

    lateinit var lexer: Lexer
    lateinit var parser: IParser<Any>
    lateinit var result: Result<Any>

    @Given("^a parser for a calculator expression$")
    fun givenCalculatorExprGoal() {
        parser = CalculatorExample.Rules.expr
        lexer = CalculatorExample.allTokens.lexer()
    }

    @Given("^a parser for zero or more calculator expressions$")
    fun givenZeroOrMoreCalcExpr() {
        parser = zeroOrMore(CalculatorExample.Rules.expr before CalculatorExample.Tokens.newline)
        lexer = CalculatorExample.allTokens.lexer()
    }

    @Given("^a parser for one or more calculator expressions$")
    fun givenOneOrMoreCalcExpr() {
        parser = oneOrMore(CalculatorExample.Rules.expr before CalculatorExample.Tokens.newline)
        lexer = CalculatorExample.allTokens.lexer()
    }

    @When("^it parses (.*)$")
    fun itParses(input: String) {
        result = lexer.lex(input).parseToEnd(parser)
    }

    @When("^it parses$")
    fun itParsesMultiline(input: String) = itParses(input)

    @Then("^the result is (\\d+)$")
    fun itReturns(value: Int) {
        assertEquals(value, result.value)
    }

    @Then("^the result is the list (.*)$")
    fun itReturns(values: List<Int>) {
        assertEquals(values, result.value)
    }

    @Then("^the parsing result is an empty list$")
    fun itReturns() {
        assertEquals(emptyList<Int>(), result.value)
    }
}