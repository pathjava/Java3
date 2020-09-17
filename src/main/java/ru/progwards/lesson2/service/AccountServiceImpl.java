package ru.progwards.lesson2.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import ru.progwards.lesson2.account.IAccount;
import ru.progwards.lesson2.exceptions.NotEnoughMoneyException;
import ru.progwards.lesson2.exceptions.UnknownAccountException;
import ru.progwards.lesson2.store.StoreImpl;

import java.util.Objects;

public class AccountServiceImpl implements AccountService, ApplicationContextAware {

    private StoreImpl<IAccount> store;

    @Override
    public void withdraw(int accountId, int amount) throws UnknownAccountException, NotEnoughMoneyException {
        for (IAccount account : store.read()) {
            if (account.getId() == accountId) {
                int currentAmount = account.getAmount();
                try {
                    if (currentAmount < amount)
                        throw new NotEnoughMoneyException();
                    else {
                        account.setAmount(currentAmount - amount);
                        store.write(account);
                    }
                } catch (NotEnoughMoneyException e) {
                    System.out.println("На счете недостаточно средств");
                }
            }
        }
    }

    @Override
    public int balance(int accountId) throws UnknownAccountException {
        return Objects.requireNonNull(store.read().stream()
                .filter(e -> e.getId() == accountId).findFirst()
                .orElse(null)).getAmount();
    }

    @Override
    public void deposit(int accountId, int amount) throws NotEnoughMoneyException, UnknownAccountException {

    }

    @Override
    public void transfer(int from, int to, int amount) throws NotEnoughMoneyException, UnknownAccountException {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        store = applicationContext.getBean(StoreImpl.class);
    }
}
