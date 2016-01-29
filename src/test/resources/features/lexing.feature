Feature: Lexing
  A lexer must be able to take a string input and break it up into a stream of tokens. The types of tokens must be
  configurable by the user via regex.

  Background:
    Given a lexer for calculator tokens

  Scenario: Lexing an empty input
    When it lexes the input ""
    Then the result is an empty list

  Scenario: Lexing an input with an invalid token in the middle
    When it lexes the input "1+2+a+4+5"
    Then lexing fails with character 'a' at position 4

  Scenario: Lexing an input with an invalid token at the start
    When it lexes the input "b+2+3+4+5"
    Then lexing fails with character 'b' at position 0

  Scenario: Lexing an input with an invalid token at the end
    When it lexes the input "1+2+3+4+c"
    Then lexing fails with character 'c' at position 8

  Scenario: Lexing a complex valid input
    When it lexes the input "1+3*(400/(5-3))"
    Then the tokens are:
      | number |
      | plus   |
      | number |
      | times  |
      | lPar   |
      | number |
      | over   |
      | lPar   |
      | number |
      | minus  |
      | number |
      | rPar   |
      | rPar   |
    And the raw values are:
      | 1   |
      | +   |
      | 3   |
      | *   |
      | (   |
      | 400 |
      | /   |
      | (   |
      | 5   |
      | -   |
      | 3   |
      | )   |
      | )   |
    And the positions are:
      | 0  |
      | 1  |
      | 2  |
      | 3  |
      | 4  |
      | 5  |
      | 8  |
      | 9  |
      | 10 |
      | 11 |
      | 12 |
      | 13 |
      | 14 |