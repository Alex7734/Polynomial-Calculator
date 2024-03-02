package com.polynomial.calculator.polynomialcalculator.models;

public class Monomial implements Comparable<Monomial> {
    private final double coefficient;
    private final int exponent;

    public Monomial(double coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public int getExponent() {
        return exponent;
    }

    @Override
    public String toString() {
        return String.format("%.2fx^%d", coefficient, exponent);
    }

    @Override
    public int compareTo(Monomial other) {
        return Integer.compare(other.exponent, this.exponent);
    }
}