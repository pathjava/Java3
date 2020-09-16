package ru.progwards.lesson2.service;

import ru.progwards.lesson2.exceptions.NotEnoughMoneyException;
import ru.progwards.lesson2.exceptions.UnknownAccountException;

public class AccountServiceImpl implements AccountService {
    @Override
    public void withdraw(int accountId, int amount) throws NotEnoughMoneyException, UnknownAccountException {

    }

    @Override
    public void balance(int accountId) throws UnknownAccountException {

    }

    @Override
    public void deposit(int accountId, int amount) throws NotEnoughMoneyException, UnknownAccountException {

    }

    @Override
    public void transfer(int from, int to, int amount) throws NotEnoughMoneyException, UnknownAccountException {

    }
}
