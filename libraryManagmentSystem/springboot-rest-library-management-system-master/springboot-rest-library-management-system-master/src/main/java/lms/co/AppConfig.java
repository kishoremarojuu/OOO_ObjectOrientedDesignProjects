package lms.co;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lms.co.repository.BookRepository;
import lms.co.repository.UsersRepository;

@Configuration
public class AppConfig {
    @Bean
    public BookRepository booksRepo(){
        return new BookRepository();
    }
    
    @Bean
    public UsersRepository usersRepo(){
        return new UsersRepository();
    }
}

