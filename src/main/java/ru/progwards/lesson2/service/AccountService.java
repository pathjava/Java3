package ru.progwards.lesson2.service;

import ru.progwards.lesson2.exceptions.NotEnoughMoneyException;
import ru.progwards.lesson2.exceptions.UnknownAccountException;

public interface AccountService {

    void withdraw(int accountId, int amount) throws NotEnoughMoneyException, UnknownAccountException;

    void balance(int accountId) throws UnknownAccountException;

    void deposit(int accountId, int amount) throws NotEnoughMoneyException, UnknownAccountException;

    void transfer(int from, int to, int amount) throws NotEnoughMoneyException, UnknownAccountException;

}
