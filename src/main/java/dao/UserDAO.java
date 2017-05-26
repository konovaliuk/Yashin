package dao;

import model.entity.User;

import java.util.List;

public interface UserDAO {
    List<User> findAll();
    User findById(Long id);
    User findByEmail(String login);

    User create(User user);
    User update(User user);
    void delete(User user);
}
