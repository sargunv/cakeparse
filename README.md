[![Build Status](https://travis-ci.org/sargunster/CakeParse.svg?branch=master)](https://travis-ci.org/sargunster/CakeParse)[ ![Download](https://api.bintray.com/packages/sargunster/maven/CakeParse/images/download.svg) ](https://bintray.com/sargunster/maven/CakeParse/_latestVersion)

# CakeParse

CakeParse is a lexer and recursive descent parser combinator library for [Kotlin](https://kotlinlang.org/). It can parse any LL(*) grammer, and contains line/column number error reporting. A [calculator expression parser](./src/test/kotlin/me/sargunvohra/lib/cakeparse/example/Calculator.kt) example is included.

## Usage

### Step 0: Setup

Check the [releases tab](https://github.com/sargunster/CakeParse/releases) for the latest version number.

#### Gradle

``` groovy
repositories {
  jcenter()
}

dependencies {
  compile "me.sargunvohra.lib:CakeParse:<version>"
}
```

#### Kobalt

``` kotlin
dependencies {
  compile("me.sargunvohra.lib.CakeParse:<version>")
}
```

#### Manual

Download the latest version from the [releases tab](https://github.com/sargunster/CakeParse/releases) and add the jar to your classpath. This method is not reccomended.

### Step 1: Tokens

Define your tokens by name and regex. These make up the building blocks of your parser rules.

``` kotlin
import me.sargunvohra.lib.cakeparse.api.*

val number = token("number", "[0-9]+")
val plus = token("plus", "\\+")
val space = token("space", "[ \\t\\r]+", ignore = true)
// ...
```

### Step 2: Productions

Define your productions using the rules in [Combinators.kt](./src/main/kotlin/me/sargunvohra/lib/cakeparse/api/Combinators.kt) and [Convenience.kt](src/main/kotlin/me/sargunvohra/lib/cakeparse/api/Convenience.kt).

``` kotlin
val parenExpr = lPar then expr before rPar
val primExpr = number map { it.raw.toInt() } or parenExpr
val goal = oneOrMore(number)
// ...
```

### Step 3: Parse

Pass your input through a lexer, and then a parser.

``` kotlin
val tokens = setOf(token1, token2, token3)
try {
  val input = System.in
  val result = tokens.lexer().lex(input).parseToEnd(goal).value
  println("Result: $result")
} catch (e: LexerException) {
  System.err.println("Lexing error: ${e.message}")
} catch (e: ParserException) {
  System.err.println("Parsing error: ${e.message}")
}
```

### Note: Recursion

If you have a rule or system of rules that involves recursion, then you'll have to use the `ref` function to make sure it works. Make sure to explicitly specify the type of a ref. This is needed because the compiler can't infer the type of a recursive reference.

``` kotlin
// put all tokens first
val lParen = token("lParen", "\\(")
val rParen = token("rParen", "\\)")

// then put all refs
val parenRef: Parser<Token> = ref({ paren })

// then put all rules
val paren = (lParen then parenRef before rParen) or empty()
```

The best way to do this is to put your goal rule at the bottom, all rules that depend on it above, all rules that depend on those above those, etc. Anywhere a rule refers to itself or the rule below, use a `ref`.

## Build

This project uses [Kobalt](http://beust.com/kobalt/home/index.html) for its build system. Simply clone the repository and run `./kobaltw assemble`. If that doesn't work, try `./kobaltw clean test assemble`

## Contribute

Pull requests are welcome. Check the issue tracker to find things to work on.