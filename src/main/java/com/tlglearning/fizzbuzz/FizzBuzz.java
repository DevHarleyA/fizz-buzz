package com.tlglearning.fizzbuzz;

import com.tlglearning.fizzbuzz.model.Analysis;
import com.tlglearning.fizzbuzz.model.State;
import java.util.Set;

public class FizzBuzz {

  private static final int DEFAULT_UPPER_BOUND = 100;

  public static void main(String[] args) {

    try {
      int upperBound = (args.length > 0) ? Integer.parseInt(args[0]) : DEFAULT_UPPER_BOUND;
      if (upperBound < 1) {
        throw new IllegalArgumentException();
      }
      Analysis analysis = new Analysis();
      for (int counter = 0; counter <= upperBound; counter++) {
        Set<State> result = analysis.analyze(counter);
        System.out.println(result.isEmpty() ? counter : result);
      }
    } catch (IllegalArgumentException e) {
      System.out.printf("Usage: java %1$s [upperBound]%n", FizzBuzz.class.getName());
      System.out.println("Where: upperbound is a positive integer, with a default of 100");
    } finally {
      System.out.println("Thanks for playing FizzBuzz!");
    }
  }
}
