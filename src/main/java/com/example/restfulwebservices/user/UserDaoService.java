package com.example.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {

    private static final List<User> users = new ArrayList<>();
    private static int userCount = 0;

    static {
//        users.add(new User(++userCount, "Adam", LocalDate.now().minusYears(30)));
//        users.add(new User(++userCount, "Eve", LocalDate.now().minusYears(25)));
//        users.add(new User(++userCount, "Jim", LocalDate.now().minusYears(20)));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
//        user.setId(++userCount);
        users.add(user);
        return user;
    }

    public User findOne(int id) {
        Predicate<User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public User deleteById(int id) {
        Predicate<User> predicate = user -> user.getId().equals(id);
        User user = users.stream().filter(predicate).findFirst().orElse(null);
        if (user != null) {
            users.remove(user);
        }
        return user;
    }
}
