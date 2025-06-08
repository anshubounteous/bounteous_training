package JavaCollectionProblems;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class JavaCollectionProblems {

    static class Employee {
        String name;

        Employee(String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }

    static class Student {
        String name;
        String gender;

        Student(String name, String gender) {
            this.name = name;
            this.gender = gender;
        }

        String getPrefixedName() {
            return (gender.equalsIgnoreCase("Male") ? "Mr. " : "Ms. ") + name;
        }
    }

    static class Laptop {
        String processor;
        int ram;
        int graphics;
        int hdd;
        String manufactureDate;

        Laptop(String processor, int ram, int graphics, int hdd, String manufactureDate) {
            this.processor = processor;
            this.ram = ram;
            this.graphics = graphics;
            this.hdd = hdd;
            this.manufactureDate = manufactureDate;
        }

        public String toString() {
            return processor + " | " + ram + "GB RAM | " + graphics + "GB GPU | " + hdd + "GB HDD | " + manufactureDate;
        }
    }

    public static void main(String[] args) {
        // Problem 1
        Map<String, List<Employee>> departments = new HashMap<>();
        departments.put("HR", Arrays.asList(new Employee("Alice"), new Employee("Adam")));
        departments.put("IT", Arrays.asList(new Employee("Bob"), new Employee("Ben")));
        departments.put("Sales", Arrays.asList(new Employee("Charlie"), new Employee("Catherine")));

        // 1. Combine all employees
        List<Employee> allEmployees = departments.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        // 2-4. Names starting with letter and sorting
        Map<Character, List<String>> sortedGrouped = allEmployees.stream()
                .map(emp -> emp.name)
                .collect(Collectors.groupingBy(
                        name -> name.charAt(0),
                        Collectors.collectingAndThen(Collectors.toList(), list -> {
                            list.sort(String::compareTo);
                            return list;
                        })
                ));
        System.out.println("Grouped by starting letter: " + sortedGrouped);

        // 5. Create 5 sports teams randomly
        Collections.shuffle(allEmployees);
        List<List<Employee>> sportsTeams = new ArrayList<>();
        int teamSize = allEmployees.size() / 5;
        for (int i = 0; i < 5; i++) {
            sportsTeams.add(allEmployees.subList(i * teamSize, Math.min((i + 1) * teamSize, allEmployees.size())));
        }
        System.out.println("Sports Teams: " + sportsTeams);

        // 6. Merge teams into 3 divisions
        List<List<Employee>> divisions = new ArrayList<>();
        for (int i = 0; i < 3; i++) divisions.add(new ArrayList<>());
        for (int i = 0; i < sportsTeams.size(); i++) {
            divisions.get(i % 3).addAll(sportsTeams.get(i));
        }
        System.out.println("Divisions: " + divisions);

        // Problem 2: Character frequency
        String input = "programming";
        Map<Character, Long> freq = input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("Character Frequency: " + freq);

        // Problem 3: Prefix based on gender
        List<Student> students = Arrays.asList(
                new Student("John", "Male"),
                new Student("Sophia", "Female"),
                new Student("David", "Male")
        );
        List<String> prefixedNames = students.stream()
                .map(Student::getPrefixedName)
                .collect(Collectors.toList());
        System.out.println("Prefixed Student Names: " + prefixedNames);

        // Problem 4: Laptop filters
        List<Laptop> laptops = Arrays.asList(
                new Laptop("i7", 16, 4, 512, "2023-01-01"),
                new Laptop("i5", 8, 2, 256, "2022-03-01"),
                new Laptop("i7", 32, 6, 1024, "2024-01-01"),
                new Laptop("i5", 16, 4, 512, "2021-12-01")
        );

        int minRam = 8, minGpu = 2;
        Map<String, List<Laptop>> groupedLaptops = laptops.stream()
                .filter(l -> l.ram >= minRam && l.graphics >= minGpu)
                .collect(Collectors.groupingBy(
                        l -> l.processor,
                        Collectors.collectingAndThen(Collectors.toList(), list -> {
                            list.sort(Comparator.comparingInt((Laptop l) -> l.ram)
                                    .thenComparingInt(l -> l.hdd)
                                    .thenComparing(l -> l.manufactureDate));
                            return list;
                        })
                ));
        System.out.println("Filtered and Grouped Laptops: " + groupedLaptops);
    }
}
