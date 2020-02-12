package lms.co.controller;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lms.co.model.Book;
import lms.co.repository.BookRepository;

@RestController
@RequestMapping("/api")
@Api(value = "Book", description = "Operations about Books")
public class BooksResourceImpl implements BooksResource {

    @Autowired
    private BookRepository booksRepo;

    @Override
    @RequestMapping(value="/book/{isbn}",
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ApiOperation(value = "Get a book using its ISBN")
    public ResponseEntity<Book> getBookByIsbn(@PathVariable String isbn) {
        Book result = booksRepo.getBook(isbn);
        HttpStatus httpStatus;
        if(result != null) {
            result.removeLinks();
            result.add(linkTo(methodOn(BooksResourceImpl.class).getBookByIsbn(isbn)).withSelfRel());
            httpStatus = HttpStatus.OK;
        }else{
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<Book>(result, httpStatus);

    }


    @Override
    @RequestMapping(value = "/book",
            method = RequestMethod.POST,
            produces = APPLICATION_JSON_VALUE,
            consumes = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Add a book")
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book book) {
        booksRepo.addBook(book);
        book.add(linkTo(methodOn(BooksResourceImpl.class).getBookByIsbn(book.getIsbn())).withSelfRel());
        book.add(linkTo(methodOn(BooksResourceImpl.class).getAllBooks()).withRel("Books"));

        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @RequestMapping(value="/books",
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    @Override
    @ApiOperation(value = "List all books")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = booksRepo.getAllBooks();
        books.forEach(book -> {
            book.removeLinks();
            book.add(linkTo(methodOn(BooksResourceImpl.class).getBookByIsbn(book.getIsbn())).withSelfRel());
        });
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/book/{isbn}",
            method = RequestMethod.PUT,
            consumes = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update a book")
    public ResponseEntity<Book> updateBook(@PathVariable String isbn, @RequestBody Book book) {
        HttpStatus httpStatus;
        if(booksRepo.isBookAvailable(isbn)){
            book.setIsbn(isbn);
            booksRepo.addBook(book);
            httpStatus = HttpStatus.OK;
            book.add(linkTo(methodOn(BooksResourceImpl.class).getBookByIsbn(isbn)).withSelfRel());
            book.add(linkTo(methodOn(BooksResourceImpl.class).getAllBooks()).withRel("Books"));
        } else {
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(book,httpStatus);
    }

    @Override
    @RequestMapping(value = "/book/{isbn}",
            method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete a book")
    public ResponseEntity<Void> deleteBook(@PathVariable String isbn) {
        HttpStatus httpStatus;
        if(booksRepo.removeBook(isbn)){
            httpStatus = HttpStatus.OK;
        } else {
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(httpStatus);
    }
    
    
    @Override
    @RequestMapping(value="search/book/{name}",
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ApiOperation(value = "Get a user using its ISBN")
    public ResponseEntity<List<Book>> getBooksByTitleAuthor(@PathVariable String name) {
        Book booksearch =  new Book();
        List<Book> search = new ArrayList<Book>();
        HttpStatus httpStatus;
        
        List<Book> books = booksRepo.getAllBooks();
    	
        books.forEach(book -> {
       	 if(book.getTitle().equals(name) || book.getAuthor().equals(name))
    		{
		 
    			booksearch.setTitle(book.getTitle());
    			booksearch.setAuthor(book.getAuthor());
    			booksearch.setBorrowDate(book.getBorrowDate()); 
    			booksearch.setBorrower(book.getBorrower());
    			booksearch.setPublisher(book.getPublisher());
    			booksearch.setIsbn(book.getIsbn());
    			booksearch.setReturnDate(book.getReturnDate());
    			booksearch.setStatus(book.getStatus());
    			booksearch.setIsbn(book.getIsbn());
    			search.add(book);
    		}
       	 
	 
       });
   	 
       
        
   
        
        
        if(search.size() > 0){
        	    httpStatus = HttpStatus.OK;
            booksearch.add(linkTo(methodOn(UsersResourceImpl.class).getUserByIsbn(name)).withSelfRel());
            booksearch.add(linkTo(methodOn(UsersResourceImpl.class).getAllUsers()).withRel("Users"));
        } else {
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<List<Book>>(search, httpStatus);

    }

}
