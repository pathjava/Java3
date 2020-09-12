package task;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class FileTaskRepository implements TaskRepository {

    private final List<Task> tasks = new CopyOnWriteArrayList<>();

    @Override
    public void save(Task task) {
        tasks.add(Integer.parseInt(task.getId()), task);
    }

    @Override
    public void update(Task task) {
        delete(task.getId());
        save(task);
    }

    @Override
    public void delete(String id) {
        tasks.remove(Integer.parseInt(id));
    }

    @Override
    public List<Task> getAll() {
        return tasks;
    }

    @Override
    public Task get(String id) {
        return tasks.get(Integer.parseInt(id));
    }
}
