Feature: Errors
  The parser and lexer must handle configuration errors with appropriate exceptions.

  Scenario: Creating a lexer with no token types
    Given a set of no token types
    When a lexer is instantiated from the set
    Then the lexer throws an IllegalArgumentException

  Scenario: Unexpected token, expected end of file
    Given a parser for a calculator expression
    When it parses 1+1 1
    Then the parsing fails with unexpected token "number", expected end of file

  Scenario: Unexpected token, expected other token
    Given a parser for a calculator expression
    When it parses ()
    Then the parsing fails with unexpected token "rPar", expected "lPar"

  Scenario: Unexpected end of file, expected token
    Given a parser for a calculator expression
    When it parses (
    Then the parsing fails with unexpected end of file, expected "lPar"