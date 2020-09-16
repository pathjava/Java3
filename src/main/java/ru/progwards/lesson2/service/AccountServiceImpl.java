package ru.progwards.lesson2.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import ru.progwards.lesson2.account.IAccount;
import ru.progwards.lesson2.exceptions.NotEnoughMoneyException;
import ru.progwards.lesson2.exceptions.UnknownAccountException;
import ru.progwards.lesson2.store.StoreImpl;

public class AccountServiceImpl implements AccountService, ApplicationContextAware {

    private StoreImpl<?> store;

    @Override
    public void withdraw(int accountId, int amount) throws NotEnoughMoneyException, UnknownAccountException {

    }

    @Override
    public int balance(int accountId) throws UnknownAccountException {
        for (IAccount account : store.read()) {
            if (account.getId() == accountId)
                return account.getAmount();
        }
        throw new UnknownAccountException("Аккаунта с id" + accountId + " нет");
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
