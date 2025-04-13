package com.codebodhi.sqslib.springboot.service;

import java.util.ArrayList;
import java.util.List;

public class PrimeFactorFinder {
  public static List<Integer> getPrimeFactors(int n) {
    List<Integer> factors = new ArrayList<>();

    // Handle 2 separately to allow incrementing i by 2 later (checking only odd numbers)
    while (n % 2 == 0) {
      factors.add(2);
      n /= 2;
    }

    // Check for odd factors from 3 up to sqrt(n)
    for (int i = 3; i <= Math.sqrt(n); i += 2) {
      while (n % i == 0) {
        factors.add(i);
        n /= i;
      }
    }

    // If n is still greater than 2, it's a prime number
    if (n > 2) {
      factors.add(n);
    }

    return factors;
  }
}
