package dao.mysql;

import dao.DataSource;
import dao.PriceDAO;
import entity.Price;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class MySQLPriceDAO implements PriceDAO{
    private static final Log LOG = LogFactory.getLog(MySQLPriceDAO.class);
    private static final MySQLPriceDAO INSTANCE = new MySQLPriceDAO();

    private static final String FIND_ALL = "SELECT * FROM price";
    private static final String FIND_BY_ID = "SELECT * FROM price WHERE price.id=?";
    private static final String CREATE = "INSERT INTO price" +
            "(berth_factor, compartment_factor, deluxe_factor) VALUES" +
            "(?,?,?)";
    private static final String UPDATE = "UPDATE price " +
            "SET berth_factor=?, compartment_factor=?, deluxe_factor=? " +
            "WHERE id=?";
    private static final String DELETE = "DELETE FROM price " +
            "WHERE id=?";


    private static final String LABEL_ID = "id";
    private static final String LABEL_BERTH_FACTOR = "berth_factor";
    private static final String LABEL_COMPARTMENT_FACTOR = "compartment_factor";
    private static final String LABEL_DELUXE_FACTOR = "deluxe_factor";

    private static final int ID_FIRST_PARAMETER = 1;
    private static final int ID_FOURTH_PARAMETER = 4;

    private static final int BERTH_FACTOR = 1;
    private static final int COMPARTMENT_FACTOR = 2;
    private static final int DELUXE_FACTOR = 3;

    private static final String INFO_CREATE = "CREATE PRICE with ID = %d";
    private static final String INFO_UPDATE = "UPDATE PRICE with ID = %d";
    private static final String INFO_DELETE = "DELETE PRICE with ID = %d";

    private static final String ERROR_FIND_ALL = "Cannot get LIST of PRICES";
    private static final String ERROR_FIND_BY_ID = "Cannot get PRICE by ID = %d";
    private static final String ERROR_CREATE = "Cannot CREATE new PRICE";
    private static final String ERROR_UPDATE = "Cannot UPDATE PRICE with ID = %d";
    private static final String ERROR_DELETE = "Cannot DELETE PRICE with ID = %d";
    private static final String ERROR_CLOSING = "Cannot close()";

    private MySQLPriceDAO(){}

    public static MySQLPriceDAO getInstance(){
        return INSTANCE;
    }

    @Override
    public List<Price> findAll() {
        List<Price> result = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;

        try{
            connection = DataSource.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet set = statement.executeQuery(FIND_ALL);
            while (set.next()){
                Price price = new Price();
                price.setId(set.getLong(LABEL_ID));
                price.setBerth_factor(set.getDouble(LABEL_BERTH_FACTOR));
                price.setCompartment_factor(set.getDouble(LABEL_COMPARTMENT_FACTOR));
                price.setDeluxe_factor(set.getDouble(LABEL_DELUXE_FACTOR));

                result.add(price);
            }
        } catch (SQLException e){
            LOG.error(ERROR_FIND_ALL);
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
            connection = DataSource.getInstance().getConnection();
            statement = connection.prepareStatement(FIND_BY_ID);
            statement.setLong(ID_FIRST_PARAMETER, id);
            ResultSet set = statement.executeQuery();
            if (set.next()){
                result = new Price();
                result.setId(set.getLong(LABEL_ID));
                result.setBerth_factor(set.getDouble(LABEL_BERTH_FACTOR));
                result.setCompartment_factor(set.getDouble(LABEL_BERTH_FACTOR));
                result.setDeluxe_factor(set.getDouble(LABEL_DELUXE_FACTOR));
            }
        } catch (SQLException e){
            LOG.error(String.format(ERROR_FIND_BY_ID, id));
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
            connection = DataSource.getInstance().getConnection();
            statement = connection.prepareStatement(CREATE);
            statement.setDouble(BERTH_FACTOR, price.getBerth_factor());
            statement.setDouble(COMPARTMENT_FACTOR, price.getCompartment_factor());
            statement.setDouble(DELUXE_FACTOR, price.getDeluxe_factor());
            statement.executeUpdate();

            ResultSet set = statement.getGeneratedKeys();
            if(set.next()){
                price.setId(set.getLong(ID_FIRST_PARAMETER));
            }
        } catch (SQLException e) {
            LOG.error(ERROR_CREATE);
        } finally {
            close(connection, statement);
        }

        LOG.info(String.format(INFO_CREATE, price.getId()));
        return price;
    }

    @Override
    public Price update(Price price) {
        Connection connection = null;
        PreparedStatement statement = null;

        try{
            connection = DataSource.getInstance().getConnection();
            statement = connection.prepareStatement(UPDATE);
            statement.setDouble(BERTH_FACTOR, price.getBerth_factor());
            statement.setDouble(COMPARTMENT_FACTOR, price.getCompartment_factor());
            statement.setDouble(DELUXE_FACTOR, price.getDeluxe_factor());
            statement.setLong(ID_FOURTH_PARAMETER, price.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(String.format(ERROR_UPDATE, price.getId()));
        } finally {
            close(connection, statement);
        }

        LOG.info(String.format(INFO_UPDATE, price.getId()));
        return price;
    }

    @Override
    public void delete(Price price) {
        Connection connection = null;
        PreparedStatement statement = null;

        try{
            connection = DataSource.getInstance().getConnection();
            statement = connection.prepareStatement(DELETE);
            statement.setLong(ID_FIRST_PARAMETER, price.getId());
            statement.executeUpdate();

            ResultSet set = statement.getGeneratedKeys();
            if(set.next()){
                price.setId(set.getLong(ID_FIRST_PARAMETER));
            }
        } catch (SQLException e) {
            LOG.error(String.format(ERROR_DELETE, price.getId()));
        } finally {
            close(connection, statement);
        }

        LOG.info(String.format(INFO_DELETE, price.getId()));
    }

    private void close(Connection connection, Statement statement){
        try {
            if (connection != null) connection.close();
            if (statement!= null) statement.close();
        } catch (SQLException e) {
            LOG.error(ERROR_CLOSING);
        }
    }

}
