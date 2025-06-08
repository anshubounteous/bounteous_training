import exception.TaskNotFoundException;
import model.Employee;
import model.Task;
import service.TaskManager;
import service.TaskMonitor;
import util.TaskUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final TaskManager taskManager = new TaskManager();
    private static final TaskMonitor taskMonitor = new TaskMonitor(taskManager);

    public static void main(String[] args) throws InterruptedException {
        taskMonitor.start();

        while (true) {
            printMenu();
            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1 -> addEmployeeAndAssignTask();
                case 2 -> updateTaskStatus();
                case 3 -> listTasksForEmployee();
                case 4 -> searchTasksByKeyword();
                case 5 -> sortTasksForEmployee();
                case 6 -> showTasksDueTomorrow();
                case 7 -> showEmployeesWithPendingTasks();
                case 8 -> {
                    taskMonitor.shutdown();
                    taskMonitor.join();
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n=== Employee Task Tracker ===");
        System.out.println("1. Add Employee & Assign Task");
        System.out.println("2. Update Task Status");
        System.out.println("3. List Tasks for Employee");
        System.out.println("4. Search Tasks by Keyword");
        System.out.println("5. Sort Tasks for Employee");
        System.out.println("6. List Tasks Due Tomorrow");
        System.out.println("7. Employees with >3 Pending Tasks");
        System.out.println("8. Exit");
    }

    private static int readInt(String prompt) {
        int num;
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                num = scanner.nextInt();
                scanner.nextLine();
                break;
            } else {
                System.out.println("Invalid input. Please enter numbers only.");
                scanner.nextLine();
            }
        }
        return num;
    }

    private static void addEmployeeAndAssignTask() {
        System.out.print("Employee Name: ");
        String empName = scanner.nextLine();
        System.out.print("Employee Department: ");
        String department = scanner.nextLine();
        int empId = readInt("Employee ID: ");
        Employee employee = new Employee(empId, empName, department);

        System.out.print("Task Description: ");
        String desc = scanner.nextLine();
        int taskId = readInt("Task ID: ");
        System.out.print("Task Status (PENDING, IN_PROGRESS, COMPLETED): ");
        Task.Status status = Task.Status.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Task Due Date (YYYY-MM-DD): ");
        LocalDate dueDate = LocalDate.parse(scanner.nextLine());

        int priority = readInt("Task Priority (lower number = higher priority): ");

        Task task = new Task(taskId, desc, status, dueDate, priority);
        taskManager.assignTask(employee, task);
        System.out.println("Task assigned successfully to " + employee.getName());
    }

    private static void updateTaskStatus() {
        int empId = readInt("Employee ID: ");
        int taskId = readInt("Task ID: ");

        System.out.print("New Status (PENDING, IN_PROGRESS, COMPLETED): ");
        Task.Status status = Task.Status.valueOf(scanner.nextLine().toUpperCase());

        Employee dummyEmp = new Employee();
        dummyEmp.setId(empId);
        try {
            taskManager.updateTaskStatus(dummyEmp, taskId, status);
            System.out.println("Task status updated.");
        } catch (TaskNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void listTasksForEmployee() {
        int empId = readInt("Employee ID: ");
        Employee dummyEmp = new Employee();
        dummyEmp.setId(empId);
        List<Task> tasks = taskManager.getTasks(dummyEmp);
        if (tasks.isEmpty()) {
            System.out.println("No tasks found for employee ID " + empId);
        } else {
            tasks.forEach(System.out::println);
        }
    }

    private static void searchTasksByKeyword() {
        System.out.print("Enter keyword to search in task descriptions: ");
        String keyword = scanner.nextLine();
        List<Task> found = taskManager.searchTasksByKeyword(keyword);
        if (found.isEmpty()) {
            System.out.println("No tasks found matching keyword.");
        } else {
            found.forEach(System.out::println);
        }
    }

    private static void sortTasksForEmployee() {
        int empId = readInt("Employee ID: ");
        Employee dummyEmp = new Employee();
        dummyEmp.setId(empId);

        System.out.println("Sort by:\n1. Priority\n2. Due Date");
        int choice = readInt("");
        List<Task> sorted;
        if (choice == 1) {
            sorted = taskManager.sortTasksByPriority(dummyEmp);
        } else if (choice == 2) {
            sorted = taskManager.sortTasksByDueDate(dummyEmp);
        } else {
            System.out.println("Invalid choice.");
            return;
        }
        sorted.forEach(System.out::println);
    }

    private static void showTasksDueTomorrow() {
        taskManager.getAllTasks().values().stream()
                .flatMap(List::stream)
                .filter(t -> !t.getStatus().equals(Task.Status.COMPLETED))
                .filter(t -> t.getDueDate().equals(LocalDate.now().plusDays(1)))
                .forEach(System.out::println);
    }

    private static void showEmployeesWithPendingTasks() {
        List<Employee> employees = TaskUtils.employeesWithMoreThanPendingTasks(taskManager.getAllTasks(), 3);
        if (employees.isEmpty()) {
            System.out.println("No employees have more than 3 pending tasks.");
        } else {
            System.out.println("Employees with more than 3 pending tasks:");
            employees.forEach(System.out::println);
        }
    }
}
