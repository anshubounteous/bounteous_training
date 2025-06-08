package service;

import exception.TaskNotFoundException;
import model.Employee;
import model.Task;

import java.util.*;
import java.util.stream.Collectors;

public class TaskManager {

    // Map Employee to their List of Tasks
    private final Map<Employee, List<Task>> employeeTasks = new HashMap<>();

    public void assignTask(Employee employee, Task task) {
        employeeTasks.computeIfAbsent(employee, k -> new ArrayList<>()).add(task);
    }

    public void updateTaskStatus(Employee employee, int taskId, Task.Status status) throws TaskNotFoundException {
        Task task = findTaskById(employee, taskId);
        if (task == null) {
            throw new TaskNotFoundException("Task with ID " + taskId + " not found for employee " + employee.getName());
        }
        task.setStatus(status);
    }

    public Task findTaskById(Employee employee, int taskId) {
        List<Task> tasks = employeeTasks.get(employee);
        if (tasks == null) return null;
        return tasks.stream()
                .filter(t -> t.getId() == taskId)
                .findFirst()
                .orElse(null);
    }

    public List<Task> getTasks(Employee employee) {
        return employeeTasks.getOrDefault(employee, new ArrayList<>());
    }

    public Map<Employee, List<Task>> getAllTasks() {
        return employeeTasks;
    }

    public List<Task> searchTasksByKeyword(String keyword) {
        return employeeTasks.values().stream()
                .flatMap(List::stream)
                .filter(t -> t.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Task> sortTasksByPriority(Employee employee) {
        List<Task> tasks = new ArrayList<>(getTasks(employee));
        Collections.sort(tasks);
        return tasks;
    }

    public List<Task> sortTasksByDueDate(Employee employee) {
        List<Task> tasks = new ArrayList<>(getTasks(employee));
        tasks.sort(Comparator.comparing(Task::getDueDate));
        return tasks;
    }
}
