package com.tlglearning.fizzbuzz.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.EnumSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

class AnalysisTest {
  static final Set<State> fizzExpected = EnumSet.of(State.FIZZ);
  static final Set<State> fizzBuzzExpected = EnumSet.of(State.BUZZ, State.FIZZ);
  static final Set<State> buzzExpected = EnumSet.of(State.BUZZ);
  static final Set<State> neitherExpected = EnumSet.noneOf(State.class); // Set.of()

  private Analysis analysis;

  @BeforeEach
  public void setUp() throws Exception {
    // inside the setup, create an instance of television
    analysis = new Analysis();
  }

  // @ParameterizedTest => analyze test cases that should all be producing FIZZ
  @ParameterizedTest
  // int value => everytime you get a new value, junit will treat it as a separate test to show up separately in the test report
  @ValueSource(ints = {3, 21, 999_999_999})
  // tells us what the values are to be used in the test method => We've included normal values and extreme values
  void analyze_fizz(int value) {
    assertEquals(fizzExpected, analysis.analyze(value));
  }

  @ParameterizedTest
  // 0 is divisible, remainder is 0
  @ValueSource(ints = {0, 15, 999_999_990})
  void analyze_fizzBuzz(int value) {
    assertEquals(fizzBuzzExpected, analysis.analyze(value));
  }

  @ParameterizedTest
  @ValueSource(ints = {5, 85, 999_999_995})
  void analyze_buzz(int value) {
    assertEquals(buzzExpected, analysis.analyze(value));
  }

  @ParameterizedTest
  // name is read relative to the package that the code is in, but it could be in a different source root (in this case, it's in the resources folder)
  // when being read, it will skip the first line (numLinesToSkip)
  @CsvFileSource(resources = "neither.csv", numLinesToSkip = 1)
  void analyze_neither(int value) {
    assertEquals(neitherExpected, analysis.analyze(value));
  }

  @ParameterizedTest
  @ValueSource(ints = {-1, -3, -5, -15})
  void analyze_negative(int value) {
    assertThrows(IllegalArgumentException.class, new InvalidInvocation(value));
  }

  class InvalidInvocation implements Executable {

    private final int value;

    public InvalidInvocation(int value) {
      this.value = value;
    }

    @Override
    public void execute() throws Throwable {
      analysis.analyze(value);
    }

  }
}