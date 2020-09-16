package ru.progwards.lesson2.account;


public class Account implements IAccount{

    private int id;
    private String holder;
    private int amount;

    public Account(int id, String holder, int amount) {
        this.id = id;
        this.holder = holder;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {

    }

    public String getHolder() {
        return holder;
    }

    public int getAmount() {
        return amount;
    }
}
