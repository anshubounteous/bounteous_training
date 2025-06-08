package util;

import model.Book;
import model.Member;

import java.util.*;
import java.util.stream.Collectors;

public class SearchAndSortUtil {
    public static List<Book> sortBooksByTitle(Collection<Book> books) {
        return books.stream().sorted(Comparator.comparing(Book::getTitle)).collect(Collectors.toList());
    }

    public static List<Book> sortBooksByAuthor(Collection<Book> books) {
        return books.stream().sorted(Comparator.comparing(Book::getAuthor)).collect(Collectors.toList());
    }

    public static List<Book> findBooksByTitle(Collection<Book> books, String title) {
        return books.stream().filter(book -> book.getTitle().equalsIgnoreCase(title)).collect(Collectors.toList());
    }

    public static List<Book> findBooksByAuthor(Collection<Book> books, String author) {
        return books.stream().filter(book -> book.getAuthor().equalsIgnoreCase(author)).collect(Collectors.toList());
    }

    public static List<Member> findMembersByName(Collection<Member> members, String name) {
        return members.stream().filter(m -> m.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
    }

    public static List<Member> findMembersByEmail(Collection<Member> members, String email) {
        return members.stream().filter(m -> m.getEmail().equalsIgnoreCase(email)).collect(Collectors.toList());
    }
}
