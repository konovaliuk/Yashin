package dao.mysql;

import dao.ConnectionPool;
import dao.UserDAO;
import dao.mysql.util.LogMessageDAOUtil;
import dao.mysql.util.QueryUtil;
import model.entity.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

class MySQLUserDAO implements UserDAO{
    private static final Log LOG = LogFactory.getLog(MySQLPriceDAO.class);
    private static final MySQLUserDAO INSTANCE = new MySQLUserDAO();

    private static final String TABLE_NAME = "user";

    private static final String LABEL_ID = "id";
    private static final String LABEL_EMAIL = "email";
    private static final String LABEL_PASSWORD = "password";
    private static final String LABEL_NAME = "name";
    private static final String LABEL_SURNAME = "surname";
    private static final String LABEL_PHONE = "phone";
    private static final String LABEL_ADMIN = "admin";

    private MySQLUserDAO(){}

    static MySQLUserDAO getInstance(){
        return INSTANCE;
    }

    @Override
    public List<User> findAll() {
        List<User> result = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;

        try {
            String findAllQuery = QueryUtil.createFindAllQuery(TABLE_NAME);

            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();

            ResultSet set = statement.executeQuery(findAllQuery);

            while (set.next()) {
                result.add(getUser(set));
            }

            LOG.info(LogMessageDAOUtil.createInfoFindAll(TABLE_NAME));
        } catch (SQLException e) {
            LOG.error(LogMessageDAOUtil.createErrorFindAll(TABLE_NAME));
        } finally {
            close(connection, statement);
        }

        return result;
    }

    @Override
    public User findById(Long id) {
        List<User> result = findByParameter(LABEL_ID, id);
        if(result.size() != 1)
            return null;

        return result.get(0);
    }

    @Override
    public User findByEmail(String email) {
        List<User> result = findByParameter(LABEL_EMAIL, email);
        if(result.size() != 1)
            return null;

        return result.get(0);
    }

    @Override
    public User create(User user) {
        Connection connection = null;
        PreparedStatement statement = null;

        try{
            String createQuery = QueryUtil.createInsertQuery(
                    TABLE_NAME,
                    LABEL_EMAIL,
                    LABEL_PASSWORD,
                    LABEL_NAME,
                    LABEL_SURNAME,
                    LABEL_PHONE,
                    LABEL_ADMIN
            );

            connection = ConnectionPool.getInstance().getConnection();

            statement = connection.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getSurname());
            statement.setString(5, user.getPhone());
            statement.setBoolean(6, user.getAdmin());

            statement.executeUpdate();

            ResultSet set = statement.getGeneratedKeys();
            if(set.next()){
                user.setId(set.getLong(1));
            }

            LOG.info(LogMessageDAOUtil.createInfoCreate(TABLE_NAME, user.getId()));
        } catch (SQLException e) {
            LOG.error(LogMessageDAOUtil.createErrorCreate(TABLE_NAME));
        } finally {
            close(connection, statement);
        }

        return user;
    }

    @Override
    public User update(User user) {
        Connection connection = null;
        PreparedStatement statement = null;


        try{
            String createQuery = QueryUtil.createUpdateQuery(
                    TABLE_NAME,
                    LABEL_ID,
                    LABEL_EMAIL,
                    LABEL_PASSWORD,
                    LABEL_NAME,
                    LABEL_SURNAME,
                    LABEL_PHONE,
                    LABEL_ADMIN
            );

            connection = ConnectionPool.getInstance().getConnection();

            statement = connection.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getSurname());
            statement.setString(5, user.getPhone());
            statement.setBoolean(6, user.getAdmin());

            statement.setLong(7, user.getId());

            statement.executeUpdate();

            ResultSet set = statement.getGeneratedKeys();
            if(set.next()){
                user.setId(set.getLong(1));
            }

            LOG.info(LogMessageDAOUtil.createInfoCreate(TABLE_NAME, user.getId()));
        } catch (SQLException e) {
            LOG.error(LogMessageDAOUtil.createErrorCreate(TABLE_NAME));
        } finally {
            close(connection, statement);
        }

        return user;
    }

    @Override
    public void delete(User user) {
        Connection connection = null;
        PreparedStatement statement = null;

        try{
            String createQuery = QueryUtil.createDeleteQuery(TABLE_NAME, LABEL_ID);

            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(createQuery);

            statement.setLong(1, user.getId());
            statement.executeUpdate();

            LOG.info(LogMessageDAOUtil.createInfoDelete(TABLE_NAME, user.getId()));
        } catch (SQLException e) {
            LOG.error(LogMessageDAOUtil.createErrorCreate(TABLE_NAME));
        } finally {
            close(connection, statement);
        }
    }

    private void close(Connection connection, Statement statement){
        try {
            if (connection != null) connection.close();
            if (statement!= null) statement.close();
        } catch (SQLException e) {
            LOG.error(LogMessageDAOUtil.createErrorClose());
        }
    }

    private User getUser(ResultSet set) throws SQLException{
        User result = new User();
        result.setId(set.getLong(LABEL_ID));
        result.setEmail(set.getString(LABEL_EMAIL));
        result.setPassword(set.getString(LABEL_PASSWORD));

        result.setName(set.getString(LABEL_NAME));
        result.setSurname(set.getString(LABEL_SURNAME));
        result.setPhone(set.getString(LABEL_PHONE));

        result.setAdmin(set.getBoolean(LABEL_ADMIN));
        return result;
    }

    private List<User> findByParameter(String label, Object parameter){
        List<User> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;

        try{
            String findByIdQuery = QueryUtil.createFindByParameterQuery(TABLE_NAME, label);

            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(findByIdQuery);
            statement.setObject(1, parameter);
            ResultSet set = statement.executeQuery();
            while (set.next()){
                result.add(getUser(set));
            }

            LOG.info(LogMessageDAOUtil.createInfoFindByParameter(TABLE_NAME, label, parameter));
        } catch (SQLException e){
            LOG.error(LogMessageDAOUtil.createErrorFindByParameter(TABLE_NAME, label, parameter));
        } finally {
            close(connection, statement);
        }

        return result;
    }
}
