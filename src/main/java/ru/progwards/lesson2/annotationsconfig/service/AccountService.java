package ru.progwards.lesson2.annotationsconfig.service;

import ru.progwards.lesson2.annotationsconfig.exceptions.NotEnoughMoneyException;
import ru.progwards.lesson2.annotationsconfig.exceptions.UnknownAccountException;

public interface AccountService {

    void withdraw(int accountId, int amount) throws NotEnoughMoneyException, UnknownAccountException;

    int balance(int accountId) throws UnknownAccountException;

    void deposit(int accountId, int amount) throws NotEnoughMoneyException, UnknownAccountException;

    void transfer(int from, int to, int amount) throws NotEnoughMoneyException, UnknownAccountException;

}
