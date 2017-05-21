package dao.mysql;

import dao.UserDAO;
import entity.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

class MySQLUserDAO implements UserDAO{
    private static final Log LOG = LogFactory.getLog(MySQLPriceDAO.class);
    private static final MySQLUserDAO INSTANCE = new MySQLUserDAO();

    private MySQLUserDAO(){}

    public static MySQLUserDAO getInstance(){
        return INSTANCE;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public List<User> findAdmins() {
        return null;
    }

    @Override
    public List<User> findUsers() {
        return null;
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public void delete(User user) {

    }
}
