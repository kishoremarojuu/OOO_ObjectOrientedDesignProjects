package lms.co.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import lms.co.model.Book;

@Component
public class BookRepository {
    HashMap<String, Book> books = new HashMap<>();

    public void addBook(Book book){

        books.put(book.getIsbn(), book);
    }

    public Book getBook(String isbn){
        return books.get(isbn);
    }

    public List<Book> getAllBooks(){
        return new ArrayList<>(books.values());
    }

    public Boolean isBookAvailable(String isbn) {
        return books.containsKey(isbn);
    }

    public Boolean removeBook(String isbn) {
        if (books.containsKey(isbn)){
            books.remove(isbn);
            return true;
        }else{
            return false;
        }
    }
}
