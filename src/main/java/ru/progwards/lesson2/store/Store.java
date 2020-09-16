package ru.progwards.lesson2.store;

import java.util.List;

public interface Store<E> {

    void write(E item);

    List<E> read();

}
