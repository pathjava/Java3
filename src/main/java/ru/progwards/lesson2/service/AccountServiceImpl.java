package ru.progwards.lesson2.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import ru.progwards.lesson2.account.IAccount;
import ru.progwards.lesson2.exceptions.NotEnoughMoneyException;
import ru.progwards.lesson2.exceptions.UnknownAccountException;
import ru.progwards.lesson2.store.Store;
import ru.progwards.lesson2.store.StoreImpl;


public class AccountServiceImpl implements AccountService, ApplicationContextAware {

    private StoreImpl<IAccount> store;
    private static boolean flag = true;

    @Override
    public void withdraw(int accountId, int amount) throws UnknownAccountException, NotEnoughMoneyException {
        if (checkAmountNotNull(amount)) return;
        try {
            for (IAccount account : store.read()) {
                if (account.getId() == accountId) {
                    int currentAmount = account.getAmount() - amount;
                    try {
                        if (currentAmount < 0)
                            throw new NotEnoughMoneyException();
                    } catch (NotEnoughMoneyException e) {
                        System.out.println("На счете " + accountId + " недостаточно средств");
                        flag = false;
                        return;
                    }
                    account.setAmount(currentAmount);
                    store.write(account);
                    return;
                }
            }
            throw new UnknownAccountException();
        } catch (UnknownAccountException e) {
            System.out.println("Аккаунта с id" + accountId + " нет");
        }
    }

    @Override
    public int balance(int accountId) throws UnknownAccountException {
        int amount = -1;
        if (!checkIdNotNegative(accountId)) {
            for (IAccount account : store.read())
                if (account.getId() == accountId)
                    amount = account.getAmount();
        }
        try {
            if (amount == -1)
                throw new UnknownAccountException();
        } catch (UnknownAccountException e) {
            System.out.println("Аккаунта с id" + accountId + " нет");
        }
        return amount;
    }

    @Override
    public void deposit(int accountId, int amount) throws NotEnoughMoneyException, UnknownAccountException {
        if (checkAmountNotNull(amount)) return;
        if (checkIdNotNegative(accountId)) return;
        try {
            for (IAccount account : store.read()) {
                if (account.getId() == accountId) {
                    account.setAmount(account.getAmount() + amount);
                    store.write(account);
                    return;
                }
            }
            throw new UnknownAccountException();
        } catch (UnknownAccountException e) {
            System.out.println("Аккаунта с id" + accountId + " нет");
        }
    }

    @Override
    public void transfer(int from, int to, int amount) throws NotEnoughMoneyException, UnknownAccountException {
        if (checkAmountNotNull(amount)) return;
        if (checkIdNotNegative(from)) return;
        if (checkIdNotNegative(to)) return;
        withdraw(from, amount);
        if (flag)
            deposit(to, amount);
    }

    private boolean checkAmountNotNull(int amount) {
        try {
            if (amount <= 0)
                throw new NotEnoughMoneyException();
        } catch (NotEnoughMoneyException e) {
            System.out.println("Сумма операции не может равняться или быть меньше 0");
            return true;
        }
        return false;
    }

    private boolean checkIdNotNegative(int accountId) {
        try {
            if (accountId < 0)
                throw new NotEnoughMoneyException();
        } catch (NotEnoughMoneyException e) {
            System.out.println("Id аккаунта не может быть меньше 0");
            return true;
        }
        return false;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        store = applicationContext.getBean("store", StoreImpl.class);
    }
}
