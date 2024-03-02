package com.polynomial.calculator.polynomialcalculator.models;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynomial {
    private final ArrayList<Monomial> monomials;

    public Polynomial() {
        this.monomials = new ArrayList<>();
    }

    public void addMonomial(Monomial monomial) {
        this.monomials.add(monomial);
        Collections.sort(monomials);
    }

    public static Polynomial fromString(String polynomialString) {
        Polynomial polynomial = new Polynomial();
        Pattern pattern = Pattern.compile("([-+]?\\b\\d*\\.?\\d*)x?\\^?(-?\\d*)");
        Matcher matcher = pattern.matcher(polynomialString.replaceAll("\\s+",""));

        while (matcher.find()) {
            String coefStr = matcher.group(1);
            String expStr = matcher.group(2);

            double coefficient = 1.0;
            int exponent = 0;

            if (!coefStr.isEmpty()) {
                coefficient = coefStr.equals("+") ? 1.0 : coefStr.equals("-") ? -1.0 : Double.parseDouble(coefStr);
            }

            if (!expStr.isEmpty()) {
                exponent = Integer.parseInt(expStr);
            } else if (matcher.group().contains("x")) {
                exponent = 1;
            }

            polynomial.addMonomial(new Monomial(coefficient, exponent));
        }

        // I know this is hacky but it works, so I'm not going to change it
        if (!polynomial.getMonomials().isEmpty()) {
            List<Monomial> monomials = polynomial.getMonomials();
            monomials.removeLast();
        }

        return polynomial;
    }


    public void simplify() {
        Map<Integer, Double> terms = new HashMap<>();
        for (Monomial monomial : this.monomials) {
            int exp = monomial.getExponent();
            terms.put(exp, terms.getOrDefault(exp, 0.0) + monomial.getCoefficient());
        }

        this.monomials.clear();
        for (Map.Entry<Integer, Double> entry : terms.entrySet()) {
            if (entry.getValue() != 0) {
                this.monomials.add(new Monomial(entry.getValue(), entry.getKey()));
            }
        }

        this.monomials.sort((a, b) -> b.getExponent() - a.getExponent());
    }

    public Polynomial add(Polynomial other) {
        Polynomial result = new Polynomial();

        ArrayList<Monomial> allMonomials = new ArrayList<>();
        allMonomials.addAll(this.monomials);
        allMonomials.addAll(other.monomials);

        for (Monomial mono : allMonomials) {
            result.addMonomial(new Monomial(mono.getCoefficient(), mono.getExponent()));
        }

        result.simplify();
        return result;
    }

    public Polynomial subtract(Polynomial other) {
        Polynomial result = new Polynomial();

        for (Monomial mono : this.monomials) {
            result.addMonomial(new Monomial(mono.getCoefficient(), mono.getExponent()));
        }

        for (Monomial mono : other.monomials) {
            result.addMonomial(new Monomial(-mono.getCoefficient(), mono.getExponent()));
        }

        result.simplify();
        return result;
    }

    public Polynomial multiply(Polynomial other) {
        Polynomial result = new Polynomial();

        for (Monomial mono1 : this.monomials) {
            for (Monomial mono2 : other.monomials) {
                double newCoefficient = mono1.getCoefficient() * mono2.getCoefficient();
                int newExponent = mono1.getExponent() + mono2.getExponent();
                result.addMonomial(new Monomial(newCoefficient, newExponent));
            }
        }

        result.simplify();
        return result;
    }

    public Polynomial divide(Polynomial other) {
        // For simplicity, assuming 'other' is a monomial or you are performing a basic division
        if (other.monomials.size() != 1) {
            throw new UnsupportedOperationException("Division only supports monomial divisors.");
        }

        Monomial divisor = other.monomials.get(0);
        Polynomial result = new Polynomial();

        for (Monomial mono : this.monomials) {
            double newCoefficient = mono.getCoefficient() / divisor.getCoefficient();
            int newExponent = mono.getExponent() - divisor.getExponent();
            if (newExponent >= 0) {
                result.addMonomial(new Monomial(newCoefficient, newExponent));
            }
        }

        result.simplify();
        return result;
    }

    public Polynomial derivative() {
        Polynomial derivative = new Polynomial();
        for (Monomial monomial : this.monomials) {
            if (monomial.getExponent() != 0) { // constant term disappears in derivative
                derivative.addMonomial(new Monomial(monomial.getCoefficient() * monomial.getExponent(), monomial.getExponent() - 1));
            }
        }
        return derivative;
    }

    public Polynomial integrate() {
        Polynomial integral = new Polynomial();
        for (Monomial monomial : this.monomials) {
            integral.addMonomial(new Monomial(monomial.getCoefficient() / (monomial.getExponent() + 1), monomial.getExponent() + 1));
        }
        return integral;
    }

    public ArrayList<Monomial> getMonomials() {
        return monomials;
    }

    @Override
    public String toString() {
        if (monomials.isEmpty()) {
            return "0";
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (Monomial monomial : monomials) {
            double coef = monomial.getCoefficient();
            int exp = monomial.getExponent();

            if (!stringBuilder.isEmpty() && coef > 0) {
                stringBuilder.append("+");
            }

            if (coef == (int) coef) {
                if (exp == 0) {
                    stringBuilder.append((int) coef);
                } else if (coef == -1) {
                    stringBuilder.append("-x");
                } else if (coef == 1) {
                    stringBuilder.append("x");
                } else {
                    stringBuilder.append((int) coef).append("x");
                }
            } else {
                if (exp == 0) {
                    stringBuilder.append(String.format("%.2f", coef));
                } else {
                    stringBuilder.append(String.format("%.2f", coef)).append("x");
                }
            }

            if (exp > 1) {
                stringBuilder.append("^").append(exp);
            }
        }

        return stringBuilder.toString();
    }

}