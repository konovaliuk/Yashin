package dao.mysql;

import dao.DataSource;
import dao.PriceDAO;
import entity.Price;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

class MySQLPriceDAO implements PriceDAO{
    private static final Log LOG = LogFactory.getLog(MySQLPriceDAO.class);
    private static final MySQLPriceDAO INSTANCE = new MySQLPriceDAO();

    private static final String FIND_ALL = "SELECT * FROM price";
    private static final String FIND_BY_ID = "SELECT * FROM price WHERE price.id=?";

    private MySQLPriceDAO(){}

    public static MySQLPriceDAO getInstance(){
        return INSTANCE;
    }

    @Override
    public List<Price> findAll() {
        List<Price> result = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try{
            connection = DataSource.getInstance().getConnection();
            statement = connection.createStatement();
            set = statement.executeQuery(FIND_ALL);
            while (set.next()){
                Price price = new Price();
                price.setId(set.getLong("id"));
                price.setBerth_factor(set.getDouble("berth_factor"));
                price.setCompartment_factor(set.getDouble("berth_factor"));
                price.setDeluxe_factor(set.getDouble("deluxe_factor"));

                result.add(price);
            }

        } catch (IOException e){
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) connection.close();
                if (statement!= null) statement.close();
                if (set != null) set.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    @Override
    public Price findById(Long id) {
        Price result = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try{
            connection = DataSource.getInstance().getConnection();
            statement = connection.createStatement();
            set = statement.executeQuery(FIND_ALL);
            if (set.next()){
                result = new Price();
                result.setId(set.getLong("id"));
                result.setBerth_factor(set.getDouble("berth_factor"));
                result.setCompartment_factor(set.getDouble("berth_factor"));
                result.setDeluxe_factor(set.getDouble("deluxe_factor"));
            }

        } catch (IOException e){
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) connection.close();
                if (statement!= null) statement.close();
                if (set != null) set.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    @Override
    public Price create(Price price) {
        return null;
    }

    @Override
    public Price update(Price price) {
        return null;
    }

    @Override
    public void delete(Price price) {

    }

}
