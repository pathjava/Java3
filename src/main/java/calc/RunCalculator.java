package calc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RunCalculator {

    private static final ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
    private static final ICalculator iCalc = context.getBean("calculator", ICalculator.class);
    private static final Calculator calculator = new Calculator(iCalc);

    public static void runCalc(String str) {
        String[] arg = str.split("\\+|\\-|\\*|\\/");
        if (arg.length != 2)
            throw new IllegalArgumentException("Неверное количество аргументов");

        String symbol = "";
        Pattern p = Pattern.compile("\\+|\\-|\\*|\\/", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(str);
        while (m.find()) {
            symbol = String.valueOf(str.charAt(m.start()));
        }

        int first;
        int second;
        try {
            first = Integer.parseInt(arg[0].trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Первым аргументом было введено не число");
        }
        try {
            second = Integer.parseInt(arg[1].trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Вторым аргументом было введено не число");
        }
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
            case "*":
                result = calculator.getICalc().mult(first, second);
                break;
            default:
                throw new IllegalArgumentException("Разрешено использовать только эти символы: +,-,/,*");
        }
        System.out.println(result);
    }

    public static void main(String[] args) {

        while (true) {
            System.out.println("Введите числа и символ математической операции или stop:");
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String str = br.readLine();
                if (str.toLowerCase().equals("stop"))
                    return;
                runCalc(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
