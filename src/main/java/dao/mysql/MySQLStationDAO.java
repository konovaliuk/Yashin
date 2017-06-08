package dao.mysql;

import dao.ConnectionPool;
import dao.StationDAO;
import dao.mysql.util.LogMessageDAOUtil;
import dao.mysql.util.QueryDAOUtil;
import model.entity.Station;
import java.util.logging.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class MySQLStationDAO implements StationDAO{
    private static final Logger LOG = Logger.getLogger(MySQLStationDAO.class.getName());
    private static final MySQLStationDAO INSTANCE = new MySQLStationDAO();

    private static final String TABLE_NAME = "station";

    private static final String LABEL_ID = "id";
    private static final String LABEL_NAME = "name";

    private MySQLStationDAO(){}

    static MySQLStationDAO getInstance(){
        return INSTANCE;
    }

    @Override
    public List<Station> findAll() {
        List<Station> result = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;

        try {
            String findAllQuery = QueryDAOUtil.createFindAllQuery(TABLE_NAME);

            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();

            ResultSet set = statement.executeQuery(findAllQuery);

            while (set.next()) {
                result.add(getStation(set));
            }

            LOG.info(LogMessageDAOUtil.createInfoFindAll(TABLE_NAME));
        } catch (SQLException e) {
            LOG.severe(LogMessageDAOUtil.createErrorFindAll(TABLE_NAME));
        } finally {
            close(connection, statement);
        }

        return result;
    }

    @Override
    public Station findById(Long id) {
        Station result = null;
        Connection connection = null;
        PreparedStatement statement = null;

        try{
            String findByIdQuery = QueryDAOUtil.createFindByParameterQuery(TABLE_NAME, LABEL_ID);

            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(findByIdQuery);
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();
            if (set.next()){
                result = getStation(set);
            }

            LOG.info(LogMessageDAOUtil.createInfoFindByParameter(TABLE_NAME, LABEL_ID, id));
        } catch (SQLException e){
            LOG.severe(LogMessageDAOUtil.createErrorFindByParameter(TABLE_NAME, LABEL_ID, id));
        } finally {
            close(connection, statement);
        }

        return result;
    }

    @Override
    public Station create(Station station) {
        Connection connection = null;
        PreparedStatement statement = null;

        try{
            String createQuery = QueryDAOUtil.createInsertQuery(
                    TABLE_NAME,
                    LABEL_NAME
            );

            connection = ConnectionPool.getInstance().getConnection();

            statement = connection.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, station.getName());

            statement.executeUpdate();

            ResultSet set = statement.getGeneratedKeys();
            if(set.next()){
                station.setId(set.getLong(1));
            }

            LOG.info(LogMessageDAOUtil.createInfoCreate(TABLE_NAME, station.getId()));
        } catch (SQLException e) {
            LOG.severe(LogMessageDAOUtil.createErrorCreate(TABLE_NAME));
        } finally {
            close(connection, statement);
        }

        return station;
    }

    @Override
    public Station update(Station station) {
        Connection connection = null;
        PreparedStatement statement = null;


        try{
            String createQuery = QueryDAOUtil.createUpdateQuery(
                    TABLE_NAME,
                    LABEL_ID,
                    LABEL_NAME
            );

            connection = ConnectionPool.getInstance().getConnection();

            statement = connection.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, station.getName());
            statement.setLong(2, station.getId());

            statement.executeUpdate();

            LOG.info(LogMessageDAOUtil.createInfoUpdate(TABLE_NAME, station.getId()));
        } catch (SQLException e) {
            LOG.severe(LogMessageDAOUtil.createErrorUpdate(TABLE_NAME, station.getId()));
        } finally {
            close(connection, statement);
        }

        return station;
    }

    @Override
    public void delete(Station station) {
        Connection connection = null;
        PreparedStatement statement = null;

        try{
            String createQuery = QueryDAOUtil.createDeleteQuery(TABLE_NAME, LABEL_ID);

            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(createQuery);

            statement.setLong(1, station.getId());
            statement.executeUpdate();

            LOG.info(LogMessageDAOUtil.createInfoDelete(TABLE_NAME, station.getId()));
        } catch (SQLException e) {
            LOG.severe(LogMessageDAOUtil.createErrorDelete(TABLE_NAME, station.getId()));
        } finally {
            close(connection, statement);
        }
    }

    private Station getStation(ResultSet set) throws SQLException{
        Station result = new Station();

        result.setId(set.getLong(LABEL_ID));
        result.setName(set.getString(LABEL_NAME));

        return result;
    }

    private void close(Connection connection, Statement statement){
        try {
            if (connection != null) connection.close();
            if (statement!= null) statement.close();
        } catch (SQLException e) {
            LOG.severe(LogMessageDAOUtil.createErrorClose());
        }
    }

}
