package lms.co.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;

import lms.co.model.Book;

public interface BooksResource {

    ResponseEntity<Book> getBookByIsbn(String isbn);
    ResponseEntity<Book> addBook(Book book);
    ResponseEntity<List<Book>> getAllBooks();
    ResponseEntity<Book> updateBook(String isbn, Book book);   
    ResponseEntity<Void> deleteBook(String isbn);
    ResponseEntity<List<Book>> getBooksByTitleAuthor(String name);

}
