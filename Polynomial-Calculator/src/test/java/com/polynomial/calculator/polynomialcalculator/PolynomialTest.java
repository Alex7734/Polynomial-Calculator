package com.polynomial.calculator.polynomialcalculator;

import com.polynomial.calculator.polynomialcalculator.models.Polynomial;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PolynomialTest {
    @Test
    public void testAddition() {
        Polynomial p1 = Polynomial.fromString("3x^2 + 2x");
        Polynomial p2 = Polynomial.fromString("5x^2 - 4x");
        Polynomial result = p1.add(p2);
        assertEquals("8x^2-2x", result.toString(), "The addition result is incorrect.");

        Polynomial p3 = Polynomial.fromString("-x^3 + 3x");
        Polynomial p4 = Polynomial.fromString("x^3 - 2x^2");
        Polynomial result2 = p3.add(p4);
        assertEquals("-2x^2+3x", result2.toString(), "The addition result is incorrect.");
    }

    @Test
    public void testSubtraction() {
        Polynomial p1 = Polynomial.fromString("3x^2 + 2x");
        Polynomial p2 = Polynomial.fromString("5x^2 - 4x");
        Polynomial result = p1.subtract(p2);
        assertEquals("-2x^2+6x", result.toString(), "The subtraction result is incorrect.");

        Polynomial p3 = Polynomial.fromString("-x^3 + 3x");
        Polynomial p4 = Polynomial.fromString("x^3 - 2x^2");
        Polynomial result2 = p3.subtract(p4);
        assertEquals("-2x^3+2x^2+3x", result2.toString(), "The subtraction result is incorrect.");
    }

    @Test
    public void testMultiplication(){
        Polynomial p1 = Polynomial.fromString("3x^2 + 2x");
        Polynomial p2 = Polynomial.fromString("5x^2 - 4x");
        Polynomial result = p1.multiply(p2);
        assertEquals("15x^4-2x^3-8x^2", result.toString(), "The multiplication result is incorrect.");

        Polynomial p3 = Polynomial.fromString("-x^3 + 3x");
        Polynomial p4 = Polynomial.fromString("x^3 - 2x^2");
        Polynomial result2 = p3.multiply(p4);
        assertEquals("-x^6+2x^5+3x^4-6x^3", result2.toString(), "The multiplication result is incorrect.");
    }

    @Test
    public void testDerivative() {
        Polynomial p1 = Polynomial.fromString("3x^2 + 2x");
        Polynomial result = p1.derivative();
        assertEquals("6x+2", result.toString(), "The derivative result is incorrect.");

        Polynomial p2 = Polynomial.fromString("-x^3 + 3x");
        Polynomial result2 = p2.derivative();
        assertEquals("-3x^2+3", result2.toString(), "The derivative result is incorrect.");
    }
}
