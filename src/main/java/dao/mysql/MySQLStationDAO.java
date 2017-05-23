package dao.mysql;

import dao.StationDAO;
import entity.Station;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

class MySQLStationDAO implements StationDAO{
    private static final Log LOG = LogFactory.getLog(MySQLStationDAO.class);
    private static final MySQLStationDAO INSTANCE = new MySQLStationDAO();

    private MySQLStationDAO(){}

    static MySQLStationDAO getInstance(){
        return INSTANCE;
    }

    @Override
    public List<Station> findAll() {
        return null;
    }

    @Override
    public Station findById(Long id) {
        return null;
    }

    @Override
    public Station create(Station route) {
        return null;
    }

    @Override
    public Station update(Station route) {
        return null;
    }

    @Override
    public void delete(Station route) {

    }
}
