package repository.Interface;

import model.Book;
import model.Member;

import java.util.Date;

public interface ILandingRecordRepo {

    void issueABook(Book book, Member member, Date issue_date, Date due_date);
    void returnABook(Book book, Member member, Date return_date);

}
