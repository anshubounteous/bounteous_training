import model.Book;
import model.Member;
import repository.GenericRepository;
import repository.LendingRecordRepo;
import service.LibraryService;
import service.OverdueMonitor;
import util.SearchAndSortUtil;

import java.util.*;

public class Main {

    private static int readInt(Scanner scanner, String prompt) {
        int id;
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                id = scanner.nextInt();
                scanner.nextLine();
                break;
            } else {
                System.out.println("Invalid input. Please enter numbers only.");
                scanner.nextLine();
            }
        }
        return id;
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        GenericRepository<Integer, Book> bookRepo = new GenericRepository<>();
        GenericRepository<Integer, Member> memberRepo = new GenericRepository<>();
        LendingRecordRepo lendingRecordRepo = new LendingRecordRepo();
        LibraryService service = new LibraryService(bookRepo, memberRepo, lendingRecordRepo);

        // Pass the collection of LendingRecords
        OverdueMonitor monitor = new OverdueMonitor(new ArrayList<>(lendingRecordRepo.getAll()));
        monitor.start();

        while (true) {
            System.out.println("\n===== Library Management Menu =====");
            System.out.println("1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. List All Books");
            System.out.println("6. List All Members");
            System.out.println("7. Search Books / Members");
            System.out.println("8. Sort Books");
            System.out.println("9. Exit");
            System.out.print("Enter choice: ");

            int choice;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Invalid choice. Please enter numbers only.");
                scanner.nextLine();
                continue;
            }

            try {
                switch (choice) {
                    case 1 -> {
                        int id;
                        while (true) {
                            id = readInt(scanner, "Enter book ID: ");
                            if (bookRepo.containsKey(id)) {
                                System.out.println("Book with ID " + id + " already exists. Please enter a new ID.");
                            } else {
                                break;
                            }
                        }
                        System.out.print("Enter title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter author: ");
                        String author = scanner.nextLine();

                        Book book = new Book();
                        book.setBook_id(id);
                        book.setTitle(title);
                        book.setAuthor(author);
                        book.setIs_issued(false);
                        bookRepo.add(id, book);
                        System.out.println("Book added successfully.");
                    }
                    case 2 -> {
                        int id;
                        while (true) {
                            id = readInt(scanner, "Enter member ID: ");
                            if (memberRepo.containsKey(id)) {
                                System.out.println("Member with ID " + id + " already exists. Please enter a new ID.");
                            } else {
                                break;
                            }
                        }
                        System.out.print("Enter name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter email: ");
                        String email = scanner.nextLine();

                        Member member = new Member();
                        member.setMember_id(id);
                        member.setName(name);
                        member.setEmail(email);
                        memberRepo.add(id, member);
                        System.out.println("Member added successfully.");
                    }
                    case 3 -> {
                        int bookId = readInt(scanner, "Enter book ID to issue: ");
                        int memberId = readInt(scanner, "Enter member ID: ");
                        Calendar cal = Calendar.getInstance();
                        Date issueDate = cal.getTime();
                        cal.add(Calendar.DATE, 7);
                        Date dueDate = cal.getTime();
                        service.issueBook(bookId, memberId, issueDate, dueDate);
                    }
                    case 4 -> {
                        int bookId = readInt(scanner, "Enter book ID to return: ");
                        int memberId = readInt(scanner, "Enter member ID: ");
                        service.returnBook(bookId, memberId, new Date());
                    }
                    case 5 -> {
                        System.out.println("All Books:");
                        for (Book b : bookRepo.getAll().values()) {
                            System.out.println("ID: " + b.getBook_id() + ", Title: " + b.getTitle() +
                                    ", Author: " + b.getAuthor() + ", Issued: " + b.isIs_issued());
                        }
                    }
                    case 6 -> {
                        System.out.println("All Members:");
                        for (Member m : memberRepo.getAll().values()) {
                            System.out.println("ID: " + m.getMember_id() + ", Name: " + m.getName() +
                                    ", Email: " + m.getEmail());
                        }
                    }
                    case 7 -> {
                        System.out.println("Search Options:\n1. Book by Title\n2. Book by Author\n3. Member by Name\n4. Member by Email");
                        int searchChoice = readInt(scanner, "");
                        switch (searchChoice) {
                            case 1 -> {
                                System.out.print("Enter title: ");
                                String title = scanner.nextLine();
                                var results = SearchAndSortUtil.findBooksByTitle(bookRepo.getAll().values(), title);
                                if (results.isEmpty()) System.out.println("No books found.");
                                else results.forEach(b -> System.out.println(b.getTitle()));
                            }
                            case 2 -> {
                                System.out.print("Enter author: ");
                                String author = scanner.nextLine();
                                var results = SearchAndSortUtil.findBooksByAuthor(bookRepo.getAll().values(), author);
                                if (results.isEmpty()) System.out.println("No books found.");
                                else results.forEach(b -> System.out.println(b.getTitle()));
                            }
                            case 3 -> {
                                System.out.print("Enter name: ");
                                String name = scanner.nextLine();
                                var results = SearchAndSortUtil.findMembersByName(memberRepo.getAll().values(), name);
                                if (results.isEmpty()) System.out.println("No members found.");
                                else results.forEach(m -> System.out.println(m.getName()));
                            }
                            case 4 -> {
                                System.out.print("Enter email: ");
                                String email = scanner.nextLine();
                                var results = SearchAndSortUtil.findMembersByEmail(memberRepo.getAll().values(), email);
                                if (results.isEmpty()) System.out.println("No members found.");
                                else results.forEach(m -> System.out.println(m.getName()));
                            }
                            default -> System.out.println("Invalid search option.");
                        }
                    }
                    case 8 -> {
                        System.out.println("Sort Options:\n1. By Title\n2. By Author");
                        int sortChoice = readInt(scanner, "");
                        List<Book> sortedBooks = switch (sortChoice) {
                            case 1 -> SearchAndSortUtil.sortBooksByTitle(bookRepo.getAll().values());
                            case 2 -> SearchAndSortUtil.sortBooksByAuthor(bookRepo.getAll().values());
                            default -> {
                                System.out.println("Invalid sort option.");
                                yield Collections.emptyList();
                            }
                        };
                        sortedBooks.forEach(b -> System.out.println(b.getTitle() + " by " + b.getAuthor()));
                    }
                    case 9 -> {
                        monitor.interrupt();
                        monitor.join();
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid choice. Try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
