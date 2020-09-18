package ru.progwards.lesson2.javabaseconfig.service;

import ru.progwards.lesson2.javabaseconfig.exceptions.NotEnoughMoneyException;
import ru.progwards.lesson2.javabaseconfig.exceptions.UnknownAccountException;

public interface AccountService {

    void withdraw(int accountId, int amount) throws NotEnoughMoneyException, UnknownAccountException;

    int balance(int accountId) throws UnknownAccountException;

    void deposit(int accountId, int amount) throws NotEnoughMoneyException, UnknownAccountException;

    void transfer(int from, int to, int amount) throws NotEnoughMoneyException, UnknownAccountException;

}
