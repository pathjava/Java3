package ru.progwards.lesson2.store;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StoreInitialization<E> implements Store<E> {

    private final List<E> initList;
    private final Map<Integer, E> accounts = new ConcurrentHashMap<>();
    private final static String DB_PATH = "C:\\Users\\OlegPC\\IdeaProjects\\TestSpring\\src\\main\\resources\\account.json";

    public StoreInitialization(List<E> initList) {
        this.initList = initList;
    }

    public void writer(){
        for (E e : initList) {
            write(e);
        }
    }

    @Override
    public void write(E item) {
//        try (Writer writer = new FileWriter(DB_PATH)) {
//            accounts.put(item.getId, item);
//            Gson gson = new GsonBuilder().setPrettyPrinting().create();
//            gson.toJson(accounts, writer);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public List<E> read() throws IOException {
        return null;
    }
}
