package ru.progwards.lesson2.javabaseconfig.store;


import ru.progwards.lesson2.javabaseconfig.account.Account;
import ru.progwards.lesson2.javabaseconfig.account.IAccount;

import java.util.ArrayList;
import java.util.List;

public class StoreInitializationAccounts {

    public static List<IAccount> getAccountsList() {
        List<IAccount> accountsList = new ArrayList<>();
        accountsList.add(new Account(1, "Ivan", 100));
        accountsList.add(new Account(2, "Alexey", 250));
        accountsList.add(new Account(3, "Vladimir", 350));
        accountsList.add(new Account(4, "Nik", 180));
        accountsList.add(new Account(5, "Sergey", 330));
        accountsList.add(new Account(6, "Dmitriy", 175));
        accountsList.add(new Account(7, "Roman", 305));
        accountsList.add(new Account(8, "Igor", 190));
        accountsList.add(new Account(9, "Vasiliy", 410));
        accountsList.add(new Account(10, "Petr", 535));
        return accountsList;
    }
}
