package ru.progwards.lesson1.calc;

public class AdvancedCalculator implements ICalculator{

    public int sum(int a, int b) {
        long result = (long) a + b;
        if (result > Integer.MAX_VALUE)
            throw new RuntimeException("Overflow");
        return (int) result;
    }

    public int diff(int a, int b) {
        long result = (long) a - b;
        if (result < Integer.MIN_VALUE)
            throw new RuntimeException("Overflow");
        return (int) result;
    }

    public int div(int a, int b) {
        if (b == 0)
            throw new IllegalArgumentException("Division by zero is prohibited");
        return a / b;
    }

    public int mult(int a, int b) {
        long result = (long) a * b;
        if (result > Integer.MAX_VALUE)
            throw new RuntimeException("Overflow");
        return (int) result;
    }

}
