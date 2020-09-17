package ru.progwards.lesson2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.progwards.lesson2.exceptions.NotEnoughMoneyException;
import ru.progwards.lesson2.exceptions.UnknownAccountException;
import ru.progwards.lesson2.service.AccountService;
import ru.progwards.lesson2.service.AccountServiceImpl;
import ru.progwards.lesson2.store.StoreImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class AccountClient {

    private static final ApplicationContext context = new ClassPathXmlApplicationContext("account-context.xml");
    private static final StoreImpl<?> store = context.getBean("store", StoreImpl.class);
    private static final AccountService service = context.getBean("service", AccountServiceImpl.class);

    private static void runAccountOperation(String str) {
        switch (str) {
            case "1":
                checkBalance();
                break;
            case "2":
                runWithdraw();
                break;
            case "3":
                runDeposit();
                break;
            case "4":
                runTransfer();
                break;
            default:
                showCommandInputId("Вы не выбрали номер задачи");
        }
    }

    private static void runTransfer() {
        try {
            service.transfer(1, 2, 520);
        } catch (UnknownAccountException | NotEnoughMoneyException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void runDeposit() {
        showCommandInputId("Введите id аккаунта:");
        Scanner input = new Scanner(System.in);
        String id = "";
        String amount = "";
        while (input.hasNextLine()) {
            id = input.nextLine();
            if (id.isEmpty()) {
                showCommandInputId("Вы не ввели id");
                continue;
            } else
                showCommandInputId("Введите сумму операции:");

            amount = input.nextLine();
            if (amount.isEmpty())
                showCommandInputId("Вы не ввели сумму!");
            else {
                break;
            }
        }
        try {
            service.deposit(Integer.parseInt(id), Integer.parseInt(amount));
        } catch (NotEnoughMoneyException | UnknownAccountException e) {
            System.out.println(e.getMessage());
        }
        showBalance(id);
    }

    private static void runWithdraw() {
        showCommandInputId("Введите id аккаунта:");
        Scanner input = new Scanner(System.in);
        String id = "";
        String amount = "";
        while (input.hasNextLine()) {
            id = input.nextLine();
            if (id.isEmpty()) {
                showCommandInputId("Вы не ввели id");
                continue;
            } else
                showCommandInputId("Введите сумму операции:");

            amount = input.nextLine();
            if (amount.isEmpty())
                System.out.println("Вы не ввели сумму!");
            else {
                break;
            }
        }
        try {
            service.withdraw(Integer.parseInt(id), Integer.parseInt(amount));
        } catch (UnknownAccountException | NotEnoughMoneyException e) {
            System.out.println(e.getMessage());
        }
        showBalance(id);
    }

    private static void checkBalance() {
        showCommandInputId("Введите id аккаунта:");
        Scanner input = new Scanner(System.in);
        String id = "";
        while (input.hasNextLine()) {
            id = input.nextLine();
            if (id.isEmpty())
                showCommandInputId("Вы не ввели id!");
            else
                break;
        }
        showBalance(id);
    }

    private static void showBalance(String id) {
        try {
            System.out.println("Баланс вашего счета: " + service.balance(Integer.parseInt(id)) + "\n");
        } catch (UnknownAccountException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void showCommandInputId(String s) {
        System.out.println(s);
    }


    public static void main(String[] args) {
        List<String> list = List.of("Введите условие операции с аккаунтом:\n" +
                "* Проверить баланс - введите: 1\n" +
                "* Списание со счета - введите: 2\n" +
                "* Пополнение счета - введите: 3\n" +
                "* Перевести со счета на счет - введите: 4\n" +
                "* Завершить программу - stop");
        while (true) {
            list.forEach(System.out::println);
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String str = br.readLine();
                if (str.isEmpty())
                    throw new IllegalArgumentException("Вы не ввели условие");
                if (str.toLowerCase().equals("stop"))
                    return;
                runAccountOperation(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
