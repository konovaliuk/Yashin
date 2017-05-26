package dao.mysql;

import dao.ConnectionPool;
import dao.RequestDAO;
import dao.mysql.util.LogMessageDAOUtil;
import dao.mysql.util.QueryUtil;
import model.entity.Request;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class MySQLRequestDAO implements RequestDAO{
    private static final Log LOG = LogFactory.getLog(MySQLPriceDAO.class);
    private static final MySQLRequestDAO INSTANCE = new MySQLRequestDAO();

    private static final String TABLE_NAME = "request";

    private static final String LABEL_ID = "id";
    private static final String LABEL_USER_ID = "user_id";
    private static final String LABEL_TRAIN_ID = "train_id";
    private static final String LABEL_TYPE = "type";
    private static final String LABEL_PRICE = "price";

    private MySQLRequestDAO(){}

    static MySQLRequestDAO getInstance(){
        return INSTANCE;
    }

    @Override
    public List<Request> findAll() {
        List<Request> result = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;

        try{
            String findAllQuery = QueryUtil.createFindAllQuery(TABLE_NAME);

            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet set = statement.executeQuery(findAllQuery);

            while (set.next()){
                result.add(getRequest(set));
            }
            LOG.info(LogMessageDAOUtil.createInfoFindAll(TABLE_NAME));
        } catch (SQLException e){
            LOG.error(LogMessageDAOUtil.createErrorFindAll(TABLE_NAME));
        } finally {
            close(connection, statement);
        }

        return result;
    }

    @Override
    public List<Request> findByTrain(Long id) {
        return findByParameter(id, LABEL_TRAIN_ID);
    }

    @Override
    public List<Request> findByUser(Long id) {
        return findByParameter(id, LABEL_USER_ID);
    }

    @Override
    public Request findById(Long id) {
        return findByParameter(id, LABEL_ID).get(0);
    }

    @Override
    public Request create(Request request) {
        Connection connection = null;
        PreparedStatement statement = null;

        try{
            String createQuery = QueryUtil.createInsertQuery(
                    TABLE_NAME,
                    LABEL_USER_ID,
                    LABEL_TRAIN_ID,
                    LABEL_PRICE,
                    LABEL_TYPE);

            connection = ConnectionPool.getInstance().getConnection();

            statement = connection.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, request.getUser_id());
            statement.setLong(2, request.getTrain_id());
            statement.setDouble(3, request.getPrice());
            statement.setString(4, request.getType().toString());

            statement.executeUpdate();

            ResultSet set = statement.getGeneratedKeys();
            if(set.next()){
                request.setId(set.getLong(1));
            }

            LOG.info(LogMessageDAOUtil.createInfoCreate(TABLE_NAME, request.getId()));
        } catch (SQLException e) {
            LOG.error(LogMessageDAOUtil.createErrorCreate(TABLE_NAME));
        } finally {
            close(connection, statement);
        }

        return request;
    }

    @Override
    public Request update(Request request) {
        Connection connection = null;
        PreparedStatement statement = null;

        try{
            String createQuery = QueryUtil.createUpdateQuery(TABLE_NAME, LABEL_ID,
                    LABEL_USER_ID,
                    LABEL_TRAIN_ID,
                    LABEL_PRICE,
                    LABEL_TYPE);

            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(createQuery);

            statement.setLong(1, request.getUser_id());
            statement.setLong(2, request.getTrain_id());
            statement.setDouble(3, request.getPrice());
            statement.setString(4, request.getType().toString());

            statement.setLong(5, request.getId());

            statement.executeUpdate();

            LOG.info(LogMessageDAOUtil.createInfoCreate(TABLE_NAME, request.getId()));
        } catch (SQLException e) {
            LOG.error(LogMessageDAOUtil.createErrorCreate(TABLE_NAME));
        } finally {
            close(connection, statement);
        }

        return request;
    }

    @Override
    public void delete(Request request) {
        Connection connection = null;
        PreparedStatement statement = null;

        try{
            String createQuery = QueryUtil.createDeleteQuery(TABLE_NAME, LABEL_ID);

            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(createQuery);

            statement.setLong(1, request.getId());
            statement.executeUpdate();

            LOG.info(LogMessageDAOUtil.createInfoDelete(TABLE_NAME, request.getId()));
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

    private List<Request> findByParameter(Long id, String parameterLabel){
        List<Request> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;

        try{
            String findByIdQuery = QueryUtil.createFindByParameterQuery(TABLE_NAME, parameterLabel);

            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(findByIdQuery);
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();

            while (set.next()){
                result.add(getRequest(set));
            }
            LOG.info(LogMessageDAOUtil.createInfoFindByParameter(TABLE_NAME, parameterLabel, id));
        } catch (SQLException e){
            LOG.error(LogMessageDAOUtil.createErrorFindByParameter(TABLE_NAME, parameterLabel, id));
        } finally {
            close(connection, statement);
        }

        return result;
    }

    private Request getRequest(ResultSet set) throws SQLException{
        Request request = new Request();
        request.setId(set.getLong(LABEL_ID));
        request.setTrain_id(set.getLong(LABEL_TRAIN_ID));
        request.setUser_id(set.getLong(LABEL_USER_ID));
        request.setPrice(set.getDouble(LABEL_PRICE));
        request.setType(TypePlace.valueOf(set.getString(LABEL_TYPE)));
        return request;
    }
}
