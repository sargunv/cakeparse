Feature: Parsing
  A parser must be able to recursively parse a stream of tokens. It must also be able to perform actions on certain
  rules. It must also be able to ignore certain tokens, like whitespace.

  Background:
    Given a parser for a calculator expression

  Scenario: Parse a simple expression
    When it parses 1+1
    Then the result is 2

  Scenario: Parse a complex expression
    When it parses (6*4)+5+7*4/2
    Then the result is 43

  Scenario: Parse a complex expression while ignoring spaces
    When it parses
    """
     6 * 4  	 +

       ( 5  + 7) * 4 /  2
    """
    Then the result is 48