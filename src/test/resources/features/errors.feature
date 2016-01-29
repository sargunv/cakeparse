Feature: Errors
  The parser and lexer must handle configuration errors with appropriate exceptions.

  Scenario: Creating a lexer with no token types
    Given a set of no token types
    When a lexer is instantiated from the set
    Then the lexer throws an IllegalArgumentException