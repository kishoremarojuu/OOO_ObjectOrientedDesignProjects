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
import lms.co.model.User;
import lms.co.repository.BookRepository;
import lms.co.repository.UsersRepository;

@RestController
@RequestMapping("/api")
@Api(value = "User", description = "Operations about Users")
public class UsersResourceImpl implements UsersResource {

    @Autowired
    private UsersRepository usersRepo;
   
    @Autowired
    private BookRepository booksRepo;

    @Override
    @RequestMapping(value="/user/{isbn}",
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ApiOperation(value = "Get a user using its ISBN")
    public ResponseEntity<User> getUserByIsbn(@PathVariable String isbn) {
        User result = usersRepo.getUser(isbn);
        HttpStatus httpStatus;
        if(result != null) {
            result.removeLinks();
            result.add(linkTo(methodOn(UsersResourceImpl.class).getUserByIsbn(isbn)).withSelfRel());
            httpStatus = HttpStatus.OK;
        }else{
            httpStatus = HttpStatus.NOT_FOUND;
        }
        
        return new ResponseEntity<User>(result, httpStatus);

    }


    @Override
    @RequestMapping(value = "/user",
            method = RequestMethod.POST,
            produces = APPLICATION_JSON_VALUE,
            consumes = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Add a user")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
        usersRepo.addUser(user);
        user.add(linkTo(methodOn(UsersResourceImpl.class).getUserByIsbn(user.getUserid())).withSelfRel());
        user.add(linkTo(methodOn(UsersResourceImpl.class).getAllUsers()).withRel("Users"));

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @RequestMapping(value="/users",
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    @Override
    @ApiOperation(value = "List all users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = usersRepo.getAllUsers();
        users.forEach(user -> {
            user.removeLinks();
            user.add(linkTo(methodOn(UsersResourceImpl.class).getUserByIsbn(user.getUserid())).withSelfRel());
        });
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/user/{isbn}",
            method = RequestMethod.PUT,
            consumes = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update a user")
    public ResponseEntity<User> updateUser(@PathVariable String isbn, @RequestBody User user) {
        HttpStatus httpStatus;
        if(usersRepo.isUserAvailable(isbn)){
            user.setUserid(isbn);
            usersRepo.addUser(user);
            httpStatus = HttpStatus.OK;
            user.add(linkTo(methodOn(UsersResourceImpl.class).getUserByIsbn(isbn)).withSelfRel());
            user.add(linkTo(methodOn(UsersResourceImpl.class).getAllUsers()).withRel("Users"));
        } else {
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(user,httpStatus);
    }

    @Override
    @RequestMapping(value = "/user/{isbn}",
            method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete a user")
    public ResponseEntity<Void> deleteUser(@PathVariable String isbn) {
        HttpStatus httpStatus;
        if(usersRepo.removeUser(isbn)){
            httpStatus = HttpStatus.OK;
        } else {
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(httpStatus);
    }


	@Override
    @RequestMapping(value = "lendbook/{userid}/{bookid}",
            method = RequestMethod.GET)
    @ApiOperation(value = "Lend Books To Users")
	public ResponseEntity<User> lendBooksToUsers(@PathVariable String  userid,@PathVariable String bookid) {
		List<User> users = usersRepo.getAllUsers();
		List<Book> books = booksRepo.getAllBooks();
 
		User borroweruser = new User();
		borroweruser.setUserid(userid);
	 
		books.forEach(book -> {
            if(book.getIsbn().equals(bookid)
            		&& book.getStatus().equals("Avilable")
            		&& book.getBorrower().equals("none"))
            		{
            			book.setBorrower(userid);
            			book.setStatus("Not Avilable");
            			if(borroweruser.getBook1issue().equals("none"))
            				borroweruser.setBook1issue(book.getTitle());
            			else if(borroweruser.getBook2issue().equals("none"))
            				borroweruser.setBook2issue(book.getTitle());
            			else
            				 borroweruser.setStatus("You [user id ]"+userid +"have used total limit as only max 2  books can borrowed by per user");
            				
            		}
           });
		
        users.forEach(user -> {
        	 if(user.getUserid().equals(userid))
             		{
             			 
             			borroweruser.setName(user.getName());
             			borroweruser.setAddress(user.getAddress());
             		}
        	 
        });
        
       
       
      
        return new ResponseEntity<>(borroweruser, HttpStatus.OK);
	}
	
	@Override
    @RequestMapping(value = "return/{userid}/{bookid}",
            method = RequestMethod.GET)
    @ApiOperation(value = "Lend Books To Users")
	public ResponseEntity<List<Book>> returnBooksToLib(@PathVariable String  userid,@PathVariable String bookid) {
		List<User> users = usersRepo.getAllUsers();
		List<Book> books = booksRepo.getAllBooks();
		 
		User borroweruser = new User();
		books.forEach(book -> {
            if(book.getIsbn().equals(bookid)
            		&& book.getStatus().equals("Not Avilable")
            		&& book.getBorrower().equals(userid))
            		{
            			borroweruser.setBook1return(book.getTitle());
            			book.setBorrower("none");
            			book.setStatus("Avilable");
            			 
            		}
           });
		
        users.forEach(user -> {
        	 if(user.getUserid().equals(userid))
             		{
        		 		if(user.getBook1issue().equals(borroweruser.getBook1issue()))
        		 			user.setBook1issue("none");
        		 		else if(user.getBook2issue().equals(borroweruser.getBook1issue()))
        		 			user.setBook2issue("none");
        		 		else
        		 		{
        		 			//do nothing or todo
        		 		}
        		 			
             			 
             		}
        	 
        });
        return new ResponseEntity<>(books, HttpStatus.OK);
	}
    
	
	 @Override
	    @RequestMapping(value="search/user/{name}",
	            method = RequestMethod.GET,
	            produces = APPLICATION_JSON_VALUE)
	    @ResponseStatus(HttpStatus.OK)
	    @ResponseBody
	    @ApiOperation(value = "Get a user using its ISBN")
	    public ResponseEntity<List<User>> getUsersByName(@PathVariable String name) {
	        User usersearch =  new User();
	        List<User> search = new ArrayList<User>();
	        HttpStatus httpStatus;
	        
	        List<User> users = usersRepo.getAllUsers();
        	
       	 users.forEach(user -> {
           	 if(user.getName().equals(name))
        		{
   		 
        			usersearch.setName(user.getName());
        			usersearch.setAddress(user.getAddress());
        			usersearch.setBook1issue(user.getBook1issue());
        			usersearch.setBook1return(user.getBook1return());
        			usersearch.setBook2issue(user.getBook2issue());
        			usersearch.setBook2return(user.getBook2issue());
        			usersearch.setStatus(user.getStatus());
        			usersearch.setUserid(user.getUserid());
        			
        			search.add(usersearch);
        		}
           	 
   	 
           });
       	 
           
            
       
	        
	        
	        if(search.size() > 0){
	        	    httpStatus = HttpStatus.OK;
	            usersearch.add(linkTo(methodOn(UsersResourceImpl.class).getUserByIsbn(name)).withSelfRel());
	            usersearch.add(linkTo(methodOn(UsersResourceImpl.class).getAllUsers()).withRel("Users"));
	        } else {
	            httpStatus = HttpStatus.NOT_FOUND;
	        }

	        return new ResponseEntity<List<User>>(search, httpStatus);

	    }

	
    
}
