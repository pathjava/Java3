package ru.progwards.lesson2.javabaseconfig.store;

import java.io.IOException;
import java.util.List;

public interface Store<E> {

    void write(E item);

    List<E> read() throws IOException;

}
