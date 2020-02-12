package lms.co.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;

import lms.co.model.Book;
import lms.co.model.User;

public interface UsersResource {
	ResponseEntity<User> getUserByIsbn(String isbn);
    ResponseEntity<User> addUser(User user);
    ResponseEntity<List<User>> getAllUsers();
    ResponseEntity<User> updateUser(String isbn, User user);   
    ResponseEntity<Void> deleteUser(String isbn);
	ResponseEntity<User> lendBooksToUsers(String userid, String bookid);
	ResponseEntity<List<Book>> returnBooksToLib(String userid, String bookid);
	ResponseEntity<List<User>> getUsersByName(String name);
	

}
