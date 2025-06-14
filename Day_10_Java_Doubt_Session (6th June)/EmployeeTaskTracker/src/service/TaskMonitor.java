package service;

import model.Task;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class TaskMonitor extends Thread {

    private final TaskManager taskManager;
    private volatile boolean running = true;

    public TaskMonitor(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public void run() {
        while (running) {
            try {
                checkOverdueTasks();
                Thread.sleep(60000); // sleep 1 minute
            } catch (InterruptedException e) {
                System.out.println("TaskMonitor interrupted, stopping...");
                running = false;
            }
        }
    }

    private void checkOverdueTasks() {
        LocalDate today = LocalDate.now();
        System.out.println("\n[TaskMonitor] Checking overdue tasks at " + today);

        Map<?, List<Task>> allTasks = taskManager.getAllTasks();

        allTasks.forEach((employee, tasks) -> {
            tasks.stream()
                    .filter(t -> t.getDueDate().isBefore(today) && t.getStatus() != Task.Status.COMPLETED)
                    .forEach(t -> System.out.println("Overdue Task for " + employee + ": " + t));
        });
    }

    public void shutdown() {
        running = false;
        this.interrupt();
    }
}
