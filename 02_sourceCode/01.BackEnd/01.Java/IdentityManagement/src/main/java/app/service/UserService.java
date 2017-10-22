package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.User;
import app.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public List<User> getAllUser() {
        final List<User> resUser = repository.findAll();
        resUser.forEach(user -> user.setPassword(null));
        return resUser;
    }

    public void saveUser(final User user) {
        this.repository.save(user);
    }
}
