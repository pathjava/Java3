package ru.progwards.lesson2.store;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ru.progwards.lesson2.account.IAccount;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class StoreImpl<E extends IAccount> implements Store<E> {

    private final Map<Integer, E> accounts = new ConcurrentHashMap<>();
    private List<E> initList;
    private final Type type = new TypeToken<List<E>>() {
    }.getType();
    private final static String DB_PATH = "C:\\Users\\OlegPC\\IdeaProjects\\TestSpring\\src\\main\\resources\\accounts.json";

    public void initAccounts() {
        if (accounts.size() == 0)
            initList.forEach(i -> accounts.put(i.getId(), i));
        initList.forEach(this::write);
    }

    public void setInitList(List<E> initList) {
        this.initList = initList;
    }

    @Override
    public void write(E item) {
        synchronized (this) {
            accounts.put(item.getId(), item);
            try (Writer writer = new FileWriter(DB_PATH)) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                gson.toJson(getAll(), writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<E> read() throws IOException {
        synchronized (this) {
            accounts.clear();
            String json = Files.readString(Path.of(DB_PATH));
            ArrayList<E> list = new Gson().fromJson(json, type);
            list.forEach(i -> accounts.put(i.getId(), i));
            return accounts.values().stream().collect(Collectors.toUnmodifiableList());
        }
    }

    private List<E> getAll() {
        return accounts.values().stream().collect(Collectors.toUnmodifiableList());
    }
}
