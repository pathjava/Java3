package ru.progwards.lesson2.xmlconfig;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.progwards.lesson2.xmlconfig.exceptions.NotEnoughMoneyException;
import ru.progwards.lesson2.xmlconfig.exceptions.UnknownAccountException;
import ru.progwards.lesson2.xmlconfig.service.AccountServiceImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class AccountClient implements ApplicationContextAware {

    private static AccountServiceImpl service;

    private void runAccountOperation(String str) {
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
                showCommandToInput("Вы не выбрали номер задачи\n");
        }
    }

    private void runTransfer() {
        showCommandToInput("Введите id аккаунта с которого выполняется перевод:");
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            String idFrom = getIdFromTransfer(input);
            String idTo = getIdToTransfer(input, idFrom);
            String amount = input.nextLine();
            if (amount.isEmpty()) {
                do {
                    showCommandToInput("Вы не ввели сумму!");
                    amount = input.nextLine();
                } while (amount.isEmpty());
            } else {
                try {
                    service.transfer(Integer.parseInt(idFrom), Integer.parseInt(idTo), Integer.parseInt(amount));
                } catch (UnknownAccountException | NotEnoughMoneyException e) {
                    System.out.println(e.getMessage());
                }
                showBalance(idFrom, "Баланс счета с которого выполнен перевод: ");
                showBalance(idTo, "Баланс счета на который выполнен перевод: ");
                return;
            }
        }
    }

    private String getIdFromTransfer(Scanner input) {
        String idFrom = input.nextLine();
        if (idFrom.isEmpty()) {
            do {
                showCommandToInput("Вы не ввели id с которого выполняется перевод");
                idFrom = input.nextLine();
            } while (idFrom.isEmpty());
        }
        showCommandToInput("Введите id аккаунта на который выполняется перевод:");
        return idFrom;
    }

    private String getIdToTransfer(Scanner input, String idFrom) {
        String idTo = input.nextLine();
        if (idTo.isEmpty() || idFrom.equals(idTo)) {
            do {
                showCommandToInput("Вы не ввели id на который выполняется перевод");
                idTo = input.nextLine();
            } while (idTo.isEmpty() || idFrom.equals(idTo));
        }
        showCommandToInput("Введите сумму операции:");
        return idTo;
    }

    private void runDeposit() {
        showCommandToInput("Введите id аккаунта:");
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            String id = getIdDepositAndWithDraw(input);
            String amount = input.nextLine();
            if (amount.isEmpty()) {
                do {
                    showCommandToInput("Вы не ввели сумму!");
                    amount = input.nextLine();
                } while (amount.isEmpty());
            } else {
                try {
                    service.deposit(Integer.parseInt(id), Integer.parseInt(amount));
                } catch (NotEnoughMoneyException | UnknownAccountException e) {
                    System.out.println(e.getMessage());
                }
                showBalance(id, "Баланс вашего счета: ");
                return;
            }
        }
    }

    private void runWithdraw() {
        showCommandToInput("Введите id аккаунта:");
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            String id = getIdDepositAndWithDraw(input);
            String amount = input.nextLine();
            if (amount.isEmpty()) {
                do {
                    System.out.println("Вы не ввели сумму!");
                    amount = input.nextLine();
                } while (amount.isEmpty());
            } else {
                try {
                    service.withdraw(Integer.parseInt(id), Integer.parseInt(amount));
                } catch (UnknownAccountException | NotEnoughMoneyException e) {
                    System.out.println(e.getMessage());
                }
                showBalance(id, "Баланс вашего счета: ");
                return;
            }
        }
    }

    private String getIdDepositAndWithDraw(Scanner input) {
        String id = input.nextLine();
        if (id.isEmpty()) {
            do {
                showCommandToInput("Вы не ввели id");
                id = input.nextLine();
            } while (id.isEmpty());
        }
        showCommandToInput("Введите сумму операции:");
        return id;
    }

    private void checkBalance() {
        showCommandToInput("Введите id аккаунта:");
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            String id = input.nextLine();
            if (id.isEmpty()) {
                do {
                    showCommandToInput("Вы не ввели id!");
                    id = input.nextLine();
                } while (id.isEmpty());
            } else {
                showBalance(id, "Баланс вашего счета: ");
                return;
            }
        }
    }

    private void showBalance(String id, String comment) {
        try {
            System.out.println(comment + service.balance(Integer.parseInt(id)) + "\n");
        } catch (UnknownAccountException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showCommandToInput(String s) {
        System.out.println(s);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        service = applicationContext.getBean("service" , AccountServiceImpl.class);
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("account-context.xml");
        AccountClient client = context.getBean("client", AccountClient.class);

        List<String> list = List.of("Введите условие операции с аккаунтом:\n" +
                "* Проверить баланс - введите: 1\n" +
                "* Списание со счета - введите: 2\n" +
                "* Пополнение счета - введите: 3\n" +
                "* Перевод со счета на счет - введите: 4\n" +
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
                client.runAccountOperation(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
