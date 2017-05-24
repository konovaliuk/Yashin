package dao.mysql;

import dao.ConnectionPool;
import dao.RouteDAO;
import dao.mysql.util.LogMessageUtil;
import dao.mysql.util.QueryUtil;
import entity.Route;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class MySQLRouteDAO implements RouteDAO{
    private static final Log LOG = LogFactory.getLog(MySQLPriceDAO.class);
    private static final MySQLRouteDAO INSTANCE = new MySQLRouteDAO();

    private static final String TABLE_NAME = "route";

    private static final String LABEL_ID = "id";
    private static final String LABEL_PRICE_ID = "price_id";

    private static final String LABEL_FROM_ID= "from_id";
    private static final String LABEL_TO_ID= "to_id";

    private static final String LABEL_FROM_TIME = "from_time";
    private static final String LABEL_TO_TIME = "to_time";

    private static final String LABEL_DISTANCE = "distance";

    private MySQLRouteDAO(){}

    static MySQLRouteDAO getInstance(){
        return INSTANCE;
    }

    @Override
    public List<Route> findAll() {
        List<Route> result = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;

        try{
            String findAllQuery = QueryUtil.createFindAllQuery(TABLE_NAME);

            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();

            ResultSet set = statement.executeQuery(findAllQuery);

            while (set.next()){
                result.add(getRoute(set));
            }

            LOG.info(LogMessageUtil.createInfoFindAll(TABLE_NAME));
        } catch (SQLException e){
            LOG.error(LogMessageUtil.createErrorFindAll(TABLE_NAME));
        } finally {
            close(connection, statement);
        }

        return result;
    }

    @Override
    public Route findById(Long id) {
        return findByParameter(LABEL_ID, id).get(0);
    }

    @Override
    public List<Route> findByFromId(Long id) {
        return findByParameter(LABEL_FROM_ID, id);
    }

    @Override
    public List<Route> findByToId(Long id) {
        return findByParameter(LABEL_TO_ID, id);
    }

    @Override
    public Route create(Route route) {
        Connection connection = null;
        PreparedStatement statement = null;

        try{
            String createQuery = QueryUtil.createInsertQuery(
                    TABLE_NAME,
                    LABEL_PRICE_ID,
                    LABEL_FROM_ID,
                    LABEL_TO_ID,
                    LABEL_FROM_TIME,
                    LABEL_TO_TIME,
                    LABEL_DISTANCE
            );

            connection = ConnectionPool.getInstance().getConnection();

            statement = connection.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS);

            statement.setLong(1, route.getPrice_id());
            statement.setLong(2, route.getFrom_id());
            statement.setLong(3, route.getTo_id());
            statement.setString(4, route.getFrom_time());
            statement.setString(5, route.getTo_time());
            statement.setDouble(6, route.getDistance());

            statement.executeUpdate();

            ResultSet set = statement.getGeneratedKeys();
            if(set.next()){
                route.setId(set.getLong(1));
            }

            LOG.info(LogMessageUtil.createInfoCreate(TABLE_NAME, route.getId()));
        } catch (SQLException e) {
            LOG.error(LogMessageUtil.createErrorCreate(TABLE_NAME));
        } finally {
            close(connection, statement);
        }

        return route;
    }

    @Override
    public Route update(Route route) {
        Connection connection = null;
        PreparedStatement statement = null;

        try{
            String createQuery = QueryUtil.createUpdateQuery(TABLE_NAME, LABEL_ID,
                    LABEL_PRICE_ID,
                    LABEL_FROM_ID,
                    LABEL_TO_ID,
                    LABEL_FROM_TIME,
                    LABEL_TO_TIME,
                    LABEL_DISTANCE
            );

            connection = ConnectionPool.getInstance().getConnection();

            statement = connection.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS);

            statement.setLong(1, route.getPrice_id());
            statement.setLong(2, route.getFrom_id());
            statement.setLong(3, route.getTo_id());
            statement.setString(4, route.getFrom_time());
            statement.setString(5, route.getTo_time());
            statement.setDouble(6, route.getDistance());

            statement.setLong(7, route.getId());

            statement.executeUpdate();

            LOG.info(LogMessageUtil.createInfoCreate(TABLE_NAME, route.getId()));
        } catch (SQLException e) {
            LOG.error(LogMessageUtil.createErrorCreate(TABLE_NAME));
        } finally {
            close(connection, statement);
        }

        return route;
    }

    @Override
    public void delete(Route route) {
        Connection connection = null;
        PreparedStatement statement = null;

        try{
            String createQuery = QueryUtil.createDeleteQuery(TABLE_NAME, LABEL_ID);

            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(createQuery);

            statement.setLong(1, route.getId());
            statement.executeUpdate();

            LOG.info(LogMessageUtil.createInfoDelete(TABLE_NAME, route.getId()));
        } catch (SQLException e) {
            LOG.error(LogMessageUtil.createErrorCreate(TABLE_NAME));
        } finally {
            close(connection, statement);
        }
    }


    private Route getRoute(ResultSet set) throws SQLException{
        Route result = new Route();
        result.setId(set.getLong(LABEL_ID));

        result.setPrice_id(set.getLong(LABEL_PRICE_ID));

        result.setFrom_id(set.getLong(LABEL_FROM_ID));
        result.setTo_id(set.getLong(LABEL_TO_ID));

        result.setFrom_time(set.getString(LABEL_FROM_TIME));
        result.setTo_time(set.getString(LABEL_TO_TIME));

        result.setDistance(set.getDouble(LABEL_DISTANCE));

        return result;
    }

    private List<Route> findByParameter(String label, Long parameter){
        List<Route> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;

        try{
            String findByIdQuery = QueryUtil.createFindByParameterQuery(TABLE_NAME, label);

            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(findByIdQuery);
            statement.setLong(1, parameter);
            ResultSet set = statement.executeQuery();
            while (set.next()){
                result.add(getRoute(set));
            }

            LOG.info(LogMessageUtil.createInfoFindByParameter(TABLE_NAME, label, parameter));
        } catch (SQLException e){
            LOG.error(LogMessageUtil.createErrorFindByParameter(TABLE_NAME, label, parameter));
            result = null;
        } finally {
            close(connection, statement);
        }

        return result;
    }


    private void close(Connection connection, Statement statement){
        try {
            if (connection != null) connection.close();
            if (statement!= null) statement.close();
        } catch (SQLException e) {
            LOG.error(LogMessageUtil.createErrorClose());
        }
    }

}
