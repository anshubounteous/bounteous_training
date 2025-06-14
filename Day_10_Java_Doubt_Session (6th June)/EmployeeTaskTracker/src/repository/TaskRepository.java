package repository;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository<T> {

    private List<T> taskList = new ArrayList<>();

    public void add(T task) {
        taskList.add(task);
    }

    public void remove(T task) {
        taskList.remove(task);
    }

    public List<T> getAll() {
        return taskList;
    }

    public boolean contains(T task) {
        return taskList.contains(task);
    }
}
