package util;

import model.Employee;
import model.Task;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TaskUtils {

    public static List<Task> findTasksDueTomorrow(List<Task> tasks) {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        return tasks.stream()
                .filter(t -> t.getDueDate().equals(tomorrow))
                .collect(Collectors.toList());
    }

    public static List<Employee> employeesWithMoreThanPendingTasks(Map<Employee, List<Task>> map, int threshold) {
        return map.entrySet().stream()
                .filter(e -> e.getValue().stream()
                        .filter(t -> t.getStatus() == Task.Status.PENDING)
                        .count() > threshold)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
