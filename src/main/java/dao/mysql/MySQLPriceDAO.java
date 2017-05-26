package dao.mysql;

import dao.ConnectionPool;
import dao.PriceDAO;
import dao.mysql.util.LogMessageDAOUtil;
import dao.mysql.util.QueryUtil;
import model.entity.Price;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class MySQLPriceDAO implements PriceDAO{
    private static final Log LOG = LogFactory.getLog(MySQLPriceDAO.class);
    private static final MySQLPriceDAO INSTANCE = new MySQLPriceDAO();
    private static final String TABLE_NAME = "price";

    private static final String LABEL_ID = "id";
    private static final String LABEL_BERTH_FACTOR = "berth_factor";
    private static final String LABEL_COMPARTMENT_FACTOR = "compartment_factor";
    private static final String LABEL_DELUXE_FACTOR = "deluxe_factor";

    private MySQLPriceDAO(){}

    static MySQLPriceDAO getInstance(){
        return INSTANCE;
    }

    @Override
    public List<Price> findAll() {
        List<Price> result = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;

        try{
            String findAllQuery = QueryUtil.createFindAllQuery(TABLE_NAME);

            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet set = statement.executeQuery(findAllQuery);
            while (set.next()){
                result.add(getPrice(set));
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
    public Price findById(Long id) {
        Price result = null;
        Connection connection = null;
        PreparedStatement statement = null;

        try{
            String findByIdQuery = QueryUtil.createFindByParameterQuery(TABLE_NAME, LABEL_ID);

            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(findByIdQuery);
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();
            if (set.next()){
                result = getPrice(set);
            }
        } catch (SQLException e){
            LOG.error(LogMessageDAOUtil.createErrorFindByParameter(TABLE_NAME, LABEL_ID, id));
        } finally {
            close(connection, statement);
        }


        return result;
    }

    @Override
    public Price create(Price price) {
        Connection connection = null;
        PreparedStatement statement = null;

        try{
            String createQuery = QueryUtil.createInsertQuery(
                    TABLE_NAME,
                    LABEL_BERTH_FACTOR,
                    LABEL_COMPARTMENT_FACTOR,
                    LABEL_DELUXE_FACTOR);

            connection = ConnectionPool.getInstance().getConnection();

            statement = connection.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS);
            statement.setDouble(1, price.getBerth_factor());
            statement.setDouble(2, price.getCompartment_factor());
            statement.setDouble(3, price.getDeluxe_factor());
            statement.executeUpdate();

            ResultSet set = statement.getGeneratedKeys();
            if(set.next()){
                price.setId(set.getLong(1));
            }

            LOG.info(LogMessageDAOUtil.createInfoCreate(TABLE_NAME, price.getId()));
        } catch (SQLException e) {
            LOG.error(LogMessageDAOUtil.createErrorCreate(TABLE_NAME));
        } finally {
            close(connection, statement);
        }

        return price;
    }

    @Override
    public Price update(Price price) {
        Connection connection = null;
        PreparedStatement statement = null;

        try{
            String updateQuery = QueryUtil.createUpdateQuery(TABLE_NAME, LABEL_ID,
                    LABEL_BERTH_FACTOR,
                    LABEL_COMPARTMENT_FACTOR,
                    LABEL_DELUXE_FACTOR);

            connection = ConnectionPool.getInstance().getConnection();

            statement = connection.prepareStatement(updateQuery);
            statement.setDouble(1, price.getBerth_factor());
            statement.setDouble(2, price.getCompartment_factor());
            statement.setDouble(3, price.getDeluxe_factor());
            statement.setLong(4, price.getId());

            statement.executeUpdate();

            LOG.info(LogMessageDAOUtil.createInfoUpdate(TABLE_NAME, price.getId()));
        } catch (SQLException e) {
            LOG.error(LogMessageDAOUtil.createErrorUpdate(TABLE_NAME, price.getId()));
        } finally {
            close(connection, statement);
        }

        return price;
    }

    @Override
    public void delete(Price price) {
        Connection connection = null;
        PreparedStatement statement = null;

        try{
            String deleteQuery = QueryUtil.createDeleteQuery(TABLE_NAME, LABEL_ID);

            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(deleteQuery);
            statement.setLong(1, price.getId());
            statement.executeUpdate();

            LOG.info(LogMessageDAOUtil.createInfoDelete(TABLE_NAME, price.getId()));
        } catch (SQLException e) {
            LOG.error(LogMessageDAOUtil.createErrorDelete(TABLE_NAME, price.getId()));
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

    private Price getPrice(ResultSet set) throws SQLException{
        Price result = new Price();
        result.setId(set.getLong(LABEL_ID));
        result.setBerth_factor(set.getDouble(LABEL_BERTH_FACTOR));
        result.setCompartment_factor(set.getDouble(LABEL_BERTH_FACTOR));
        result.setDeluxe_factor(set.getDouble(LABEL_DELUXE_FACTOR));

        return result;
    }

}
