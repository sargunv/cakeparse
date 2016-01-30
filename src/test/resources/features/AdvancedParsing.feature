Feature: Advanced Parsing
  A parser must support sequences such as "one or more" and "zero or more".

  Scenario: Zero expressions to a parser for zero or more expressions
    Given a parser for zero or more calculator expressions
    When it parses
    """

    """
    Then the parsing result is an empty list

  Scenario: One expression to a parser for zero or more expressions
    Given a parser for zero or more calculator expressions
    When it parses
    """
    1+1

    """
    Then the result is the list 2

  Scenario: Many expressions to a parser for zero or more expressions
    Given a parser for zero or more calculator expressions
    When it parses
    """
    1+1
    (5 + 123)
    3 * (6-12) * 4

    """
    Then the result is the list 2, 128, -72

  Scenario: One expression to a parser for one or more expressions
    Given a parser for one or more calculator expressions
    When it parses
    """
    1+1

    """
    Then the result is the list 2

  Scenario: Many expressions to a parser for one or more expressions
    Given a parser for one or more calculator expressions
    When it parses
    """
    1+1
    (5 + 123)
    3 * (6-12) * 4

    """
    Then the result is the list 2, 128, -72