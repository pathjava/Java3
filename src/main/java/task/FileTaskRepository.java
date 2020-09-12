package task;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

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

public class FileTaskRepository implements TaskRepository {

    private final Map<String, Task> tasks = new ConcurrentHashMap<>();
    private final Type type = new TypeToken<List<Task>>() {
    }.getType();
    public final static String DB_PATH = "C:\\Users\\OlegPC\\IdeaProjects\\TestSpring\\src\\main\\resources\\tasks.json";

    public FileTaskRepository() {
        try {
            readFromJson();
        } catch (IOException e) {
            saveToJson();
        }
    }

    @Override
    public void save(Task task) {
        Task temp = tasks.put(task.getId(), task);
        if (temp == null)
            saveToJson();
    }

    @Override
    public void update(Task task) {
        Task temp = tasks.remove(task.getId());
        if (temp == null)
            save(task);
    }

    @Override
    public void delete(String id) {
        Task temp = tasks.remove(id);
        if (temp != null)
            saveToJson();
    }

    @Override
    public List<Task> getAll() {
        return tasks.values().stream().collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Task get(String id) {
        return tasks.get(id);
    }

    private void saveToJson() {
        synchronized (this) {
            try (Writer writer = new FileWriter(DB_PATH)) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                gson.toJson(getAll(), writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void readFromJson() throws IOException {
        synchronized (this) {
            tasks.clear();
            String json = Files.readString(Path.of(DB_PATH));
            ArrayList<Task> list = new Gson().fromJson(json, type);
            list.forEach(i -> tasks.put(i.getId(), i));
        }
    }

    public int getSize(){
        return tasks.size();
    }
}
