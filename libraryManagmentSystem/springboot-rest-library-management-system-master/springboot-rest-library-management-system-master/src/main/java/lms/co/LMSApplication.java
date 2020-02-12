package lms.co;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import lms.co.model.Book;
import lms.co.model.User;
import lms.co.repository.BookRepository;
import lms.co.repository.UsersRepository;

@ComponentScan
@EnableAutoConfiguration
public class LMSApplication
{
    @Autowired
    private BookRepository booksRepo;
    
    @Autowired
    private UsersRepository usersRepo;


    public static void main( String[] args ){
        SpringApplication.run(LMSApplication.class, args);

    }

    @PostConstruct
    public void initApplication() throws IOException {
        booksRepo.addBook(new Book("111-1","autobiography of a yogi","Yogananda","pbs publisher","Avilable","none","none","none"));
        booksRepo.addBook(new Book("111-2","Gita","Lord Krishna","Kbs publisher","Avilable","none","none","none"));
        booksRepo.addBook(new Book("111-3","Ramayan","Tulsi das","pbs publisher","Avilable","none","none","none"));
        booksRepo.addBook(new Book("111-4","Gita","Iskon","pbs publisher","Avilable","none","none","none"));
        booksRepo.addBook(new Book("111-5","shreemad bhagwat geeta","Iskon","pbs publisher","Avilable","none","none","none"));
        
        usersRepo.addUser(new User("111-1","amit","Nainital","none","none","none","none"));
        usersRepo.addUser(new User("111-2","amit","Delhi","none","none","none","none"));
        usersRepo.addUser(new User("111-3","Harish","Lucknow","none","none","none","none"));


    }
}

