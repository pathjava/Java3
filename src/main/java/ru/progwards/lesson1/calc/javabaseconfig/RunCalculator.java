package ru.progwards.lesson1.calc.javabaseconfig;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RunCalculator {

    private static Calculator calculator;

    public void runCalc(String str) {
        String symbol = "";
        Pattern pattern = Pattern.compile("\\+|\\-|\\*|\\/", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            symbol = String.valueOf(str.charAt(matcher.start()));
        }
        if (symbol.length() == 0)
            throw new IllegalArgumentException("Разрешено использовать только эти символы: +,-,/,*");

        String[] numbers = str.split("\\+|\\-|\\*|\\/");
        if (numbers.length != 2)
            throw new IllegalArgumentException("Неверное количество аргументов");

        int first;
        int second;
        try {
            first = Integer.parseInt(numbers[0].trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Первым аргументом было введено не число");
        }
        try {
            second = Integer.parseInt(numbers[1].trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Вторым аргументом было введено не число");
        }

        int result = getResult(symbol, first, second);
        System.out.println(result);
    }

    private int getResult(String symbol, int first, int second) {
        int result;
        switch (symbol) {
            case "+":
                result = calculator.getICalc().sum(first, second);
                break;
            case "-":
                result = calculator.getICalc().diff(first, second);
                break;
            case "/":
                result = calculator.getICalc().div(first, second);
                break;
            default:
                result = calculator.getICalc().mult(first, second);
        }
        return result;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CalcConfig.class);
        calculator = context.getBean(Calculator.class);
        RunCalculator runner = context.getBean(RunCalculator.class);

        while (true) {
            System.out.println("Введите числа и символ математической операции или stop:");
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String str = br.readLine();
                if (str.isEmpty())
                    throw new IllegalArgumentException("Вы не ввели условие");
                if (str.toLowerCase().equals("stop"))
                    return;
                runner.runCalc(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
