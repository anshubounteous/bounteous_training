package repository.Interface;

import model.Book;
import java.util.HashMap;

public interface IBookRepo {

    void AddBook(Book book);
    void DeleteBook(Book book);

}
