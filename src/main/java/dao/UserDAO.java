package dao;

import entity.User;

import java.util.List;

public interface UserDAO {
    List<User> findAll();
    List<User> findAdmins();
    List<User> findUsers();
    User findById(Long id);
    User findByLogin(String login);

    User create(User user);
    User update(User user);
    void delete(User user);
}
