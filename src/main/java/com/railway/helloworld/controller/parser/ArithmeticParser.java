package com.railway.helloworld.controller.parser;

//made thanks to mistralAI
public class ArithmeticParser {

    private String expression;
    private int pos;

    public ArithmeticParser(String expression) {
        this.expression = expression;
        this.pos = 0;
    }

    public double parse() {
        double result = parseExpression();
        if (pos != expression.length()) {
            throw new IllegalArgumentException("Invalid expression");
        }
        return result;
    }

    private double parseExpression() {
        double result = parseTerm();
        while (pos < expression.length() && (expression.charAt(pos) == '+' || expression.charAt(pos) == '-')) {
            char operator = expression.charAt(pos);
            pos++;
            double nextTerm = parseTerm();
            if (operator == '+') {
                result += nextTerm;
            } else {
                result -= nextTerm;
            }
        }
        return result;
    }

    private double parseTerm() {
        double result = parseFactor();
        while (pos < expression.length() && (expression.charAt(pos) == '*' || expression.charAt(pos) == '/')) {
            char operator = expression.charAt(pos);
            pos++;
            double nextFactor = parseFactor();
            if (operator == '*') {
                result *= nextFactor;
            } else {
                result /= nextFactor;
            }
        }
        return result;
    }

    private double parseFactor() {
        if (expression.charAt(pos) == '(') {
            pos++;
            double result = parseExpression();
            if (expression.charAt(pos) == ')') {
                pos++;
                return result;
            } else {
                throw new IllegalArgumentException("Mismatched parentheses");
            }
        } else {
            return (double)parseNumber();
        }
    }

    private int parseNumber() {
        int startPos = pos;
        while (pos < expression.length() && Character.isDigit(expression.charAt(pos))) {
            pos++;
        }
        if (startPos == pos) {
            throw new IllegalArgumentException("Expected a number");
        }
        return Integer.parseInt(expression.substring(startPos, pos));
    }



}

