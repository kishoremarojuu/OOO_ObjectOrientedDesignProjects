package lms.co.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import lms.co.model.User;

@Component
public class UsersRepository {
    HashMap<String, User> users = new HashMap<>();

    public void addUser(User user){

        users.put(user.getUserid(), user);
    }

    public User getUser(String id){
        return users.get(id);
    }

    public List<User> getAllUsers(){
        return new ArrayList<>(users.values());
    }

    public Boolean isUserAvailable(String isbn) {
        return users.containsKey(isbn);
    }

    public Boolean removeUser(String isbn) {
        if (users.containsKey(isbn)){
            users.remove(isbn);
            return true;
        }else{
            return false;
        }
    }
}
