package me.sargunvohra.lib.cakeparse.test

import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber
import org.junit.runner.RunWith

@RunWith(value = Cucumber::class)
@CucumberOptions(
        format = arrayOf("pretty"),
        features = arrayOf("classpath:features")
)
class CucumberTest