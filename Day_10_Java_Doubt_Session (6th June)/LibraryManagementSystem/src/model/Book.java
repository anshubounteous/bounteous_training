package model;

public class Book {
    private int book_id;
    private String title;
    private String author;
    private boolean is_issued;


    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isIs_issued() {
        return is_issued;
    }

    public void setIs_issued(boolean is_issued) {
        this.is_issued = is_issued;
    }
}
