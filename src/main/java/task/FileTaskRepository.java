package task;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FileTaskRepository implements TaskRepository {

    private final Map<String, Task> tasks = new ConcurrentHashMap<>();

    @Override
    public void save(Task task) {

    }

    @Override
    public void update(Task task) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public List<Task> get() {
        return null;
    }

    @Override
    public Task get(String id) {
        return null;
    }
}
