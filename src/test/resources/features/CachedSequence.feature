Feature: CachedSequence
  A cached sequence is a class that caches elements from a source sequence and allows for fast dropping of elements.

  Scenario: Simple iteration through a cached sequence
    When I cache a sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
    Then the cached sequence contains 1, 2, 3, 4, 5, 6, 7, 8, 9, 10

  Scenario: Single round of dropping
    When I cache a sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
    And I drop 5 elements
    Then the cached sequence contains 6, 7, 8, 9, 10

  Scenario: Triple round of dropping
    When I cache a sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
    And I drop 1 elements
    And I drop 2 elements
    And I drop 3 elements
    Then the cached sequence contains 7, 8, 9, 10