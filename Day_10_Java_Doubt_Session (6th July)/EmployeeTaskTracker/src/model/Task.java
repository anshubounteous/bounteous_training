package model;

import java.time.LocalDate;

public class Task implements Comparable<Task> {
    public enum Status { PENDING, IN_PROGRESS, COMPLETED }

    private int id;
    private String description;
    private Status status;
    private LocalDate dueDate;
    private int priority; // Lower number = higher priority

    public Task() {}

    public Task(int id, String description, Status status, LocalDate dueDate, int priority) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
        this.priority = priority;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public int getPriority() { return priority; }
    public void setPriority(int priority) { this.priority = priority; }

    @Override
    public String toString() {
        return "Task{id=" + id + ", description='" + description + "', status=" + status +
                ", dueDate=" + dueDate + ", priority=" + priority + "}";
    }

    // Comparable by priority
    @Override
    public int compareTo(Task other) {
        return Integer.compare(this.priority, other.priority);
    }
}
