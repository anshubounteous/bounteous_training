package repository;

import model.Book;
import repository.Interface.IBookRepo;
import java.util.HashMap;

public class BookRepo implements IBookRepo {
    public HashMap<Integer, Book> bookMap = new HashMap<>();

    public void AddBook(Book book) {
        bookMap.put(book.getBook_id(), book);
    }

    public void DeleteBook(Book book) {
        bookMap.remove(book.getBook_id());
    }

    public Book getBookById(int id) {
        return bookMap.get(id);
    }

}
