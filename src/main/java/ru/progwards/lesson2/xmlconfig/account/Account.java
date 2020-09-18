package ru.progwards.lesson2.xmlconfig.account;


public class Account implements IAccount {

    private final int id;
    private final String holder;
    private int amount;

    public Account(int id, String holder, int amount) {
        this.id = id;
        this.holder = holder;
        this.amount = amount;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getHolder() {
        return holder;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
