package dao;

import model.entity.User;

import java.util.List;

/**
 * Interface to Data Access Object for <b>USER</b>
 * Provides CRUD operations
 * @author Andrii Yashin
 * @version 1.0
 */
public interface UserDAO {
    /**
     * Find all USERS in DB
     *
     * @author Andrii Yashin
     */
    List<User> findAll();

    /**
     * Find USER by ID
     *
     * @author Andrii Yashin
     */
    User findById(Long id);

    /**
     * Find USER by login
     *
     * @author Andrii Yashin
     */
    User findByEmail(String login);

    /**
     * Insert new USER
     *
     * @author Andrii Yashin
     */
    User create(User user);

    /**
     * Update USER
     *
     * @author Andrii Yashin
     */
    User update(User user);

    /**
     * Delete USER
     *
     * @author Andrii Yashin
     */
    void delete(User user);
}
