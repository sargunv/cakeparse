package me.sargunvohra.lib.cakeparse.test

import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import me.sargunvohra.lib.common.CachedSequence
import me.sargunvohra.lib.common.cached
import org.junit.Assert.*

class CachedSequenceStepDef {

    lateinit var sequence: CachedSequence<Int>

    @When("^I cache a sequence (.*)$")
    fun cacheSequence(input: List<Int>) {
        sequence = input.asSequence().constrainOnce().cached()
    }

    @When("^I drop (\\d+) elements$")
    fun dropElements(n: Int) {
        val oldValues = sequence.toList()
        val newSequence = sequence.drop(n)
        assertEquals(oldValues, sequence.toList())
        assertEquals(oldValues, sequence.toList())
        sequence = newSequence
    }

    @Then("^the cached sequence contains (.*)$")
    fun checkSequence(expected: List<Int>) {
        assertEquals(expected, sequence.toList())
        assertEquals(expected, sequence.toList())
    }
}