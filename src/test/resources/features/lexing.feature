Feature: Lexing
  A lexer must be able to take a string input and break it up into a stream of tokens. The types of tokens must be
  configurable by the user via regex.

  Background:
    Given a lexer for calculator tokens

  Scenario: Lexing an empty input
    When it lexes empty input
    Then the lexing result is an empty list

  Scenario: Lexing an input with an invalid token in the middle
    When it lexes the input 1+2+a+4+5
    Then lexing fails with character 'a' at position 4

  Scenario: Lexing an input with an invalid token at the start
    When it lexes the input b+2+3+4+5
    Then lexing fails with character 'b' at position 0

  Scenario: Lexing an input with an invalid token at the end
    When it lexes the input 1+2+3+4+c
    Then lexing fails with character 'c' at position 8

  Scenario: Lexing a complex valid input
    When it lexes the input 1+3*(400/(5-3))
    Then the token types, raw values, and positions are:
      | number | 1   | 0  |
      | plus   | +   | 1  |
      | number | 3   | 2  |
      | times  | *   | 3  |
      | lPar   | (   | 4  |
      | number | 400 | 5  |
      | over   | /   | 8  |
      | lPar   | (   | 9  |
      | number | 5   | 10 |
      | minus  | -   | 11 |
      | number | 3   | 12 |
      | rPar   | )   | 13 |
      | rPar   | )   | 14 |

  Scenario: Lexing a a multiline input
    When it lexes the input
    """
    1+-
    +

    2
    ()
    4*/

    """
    Then the line numbers are 1, 1, 1, 1, 2, 2, 3, 4, 4, 5, 5, 5, 6, 6, 6, 6