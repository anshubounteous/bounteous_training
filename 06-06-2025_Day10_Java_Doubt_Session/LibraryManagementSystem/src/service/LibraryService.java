package service;

import model.Book;
import model.LendingRecord;
import model.Member;
import repository.GenericRepository;
import repository.LendingRecordRepo;

import java.util.*;
import java.util.stream.Collectors;

public class LibraryService {
    private final GenericRepository<Integer, Book> bookRepo;
    private final GenericRepository<Integer, Member> memberRepo;
    private final LendingRecordRepo lendingRecordRepo;

    public LibraryService(GenericRepository<Integer, Book> bookRepo,
                          GenericRepository<Integer, Member> memberRepo,
                          LendingRecordRepo lendingRecordRepo) {
        this.bookRepo = bookRepo;
        this.memberRepo = memberRepo;
        this.lendingRecordRepo = lendingRecordRepo;
    }

    public void addBook(Book book) {
        if (bookRepo.containsKey(book.getBook_id())) {
            System.out.println("Book already exists with ID: " + book.getBook_id());
            return;
        }
        bookRepo.add(book.getBook_id(), book);
    }

    public void addMember(Member member) {
        if (memberRepo.containsKey(member.getMember_id())) {
            System.out.println("Member already exists with ID: " + member.getMember_id());
            return;
        }
        memberRepo.add(member.getMember_id(), member);
    }

    public void issueBook(int bookId, int memberId, Date issueDate, Date dueDate) {
        Optional<Book> bookOpt = bookRepo.get(bookId);
        Optional<Member> memberOpt = memberRepo.get(memberId);

        if (bookOpt.isEmpty()) {
            System.out.println("Book not found with ID: " + bookId);
            return;
        }
        if (memberOpt.isEmpty()) {
            System.out.println("Member not found with ID: " + memberId);
            return;
        }

        Book book = bookOpt.get();
        if (book.isIs_issued()) {
            System.out.println("Book already issued.");
            return;
        }

        book.setIs_issued(true);
        bookRepo.update(bookId, book);

        LendingRecord record = new LendingRecord();
        record.setRecord_id(lendingRecordRepo.getAll().size() + 1);
        record.setBook(book);
        record.setMember(memberOpt.get());
        record.setIssue_date(issueDate);
        record.setDue_date(dueDate);

        lendingRecordRepo.add(record);
        System.out.println("Book issued successfully.");
    }

    public void returnBook(int bookId, int memberId, Date returnDate) {
        Optional<Book> bookOpt = bookRepo.get(bookId);
        Optional<Member> memberOpt = memberRepo.get(memberId);

        if (bookOpt.isEmpty() || memberOpt.isEmpty()) {
            System.out.println("Invalid Book or Member ID");
            return;
        }

        Book book = bookOpt.get();
        book.setIs_issued(false);
        bookRepo.update(bookId, book);

        for (LendingRecord record : lendingRecordRepo.getAll()) {
            if (record.getBook().getBook_id() == bookId &&
                    record.getMember().getMember_id() == memberId &&
                    record.getReturn_date() == null) {
                record.setReturn_date(returnDate);
                System.out.println("Book returned successfully.");
                return;
            }
        }
        System.out.println("No active lending record found for this book and member.");
    }

    public void listAllBooksSortedByTitle() {
        bookRepo.getAll().values().stream()
                .sorted(Comparator.comparing(Book::getTitle))
                .forEach(book -> System.out.println(book.getBook_id() + " - " + book.getTitle()));
    }

    public void listAllBooksSortedByAuthor() {
        bookRepo.getAll().values().stream()
                .sorted(Comparator.comparing(Book::getAuthor))
                .forEach(book -> System.out.println(book.getBook_id() + " - " + book.getAuthor()));
    }

    public void searchBookByTitle(String title) {
        List<Book> books = bookRepo.getAll().values().stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
        books.forEach(book -> System.out.println(book.getBook_id() + " - " + book.getTitle()));
    }

    public void searchMemberByEmail(String email) {
        List<Member> members = memberRepo.getAll().values().stream()
                .filter(member -> member.getEmail().equalsIgnoreCase(email))
                .collect(Collectors.toList());
        members.forEach(member -> System.out.println(member.getMember_id() + " - " + member.getEmail()));
    }
}
