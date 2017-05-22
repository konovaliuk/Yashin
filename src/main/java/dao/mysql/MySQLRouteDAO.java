package dao.mysql;

import dao.RouteDAO;
import entity.Route;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

class MySQLRouteDAO implements RouteDAO{
    private static final Log LOG = LogFactory.getLog(MySQLPriceDAO.class);
    private static final MySQLRouteDAO INSTANCE = new MySQLRouteDAO();

    private MySQLRouteDAO(){}

    public static MySQLRouteDAO getInstance(){
        return INSTANCE;
    }

    @Override
    public List<Route> findAll() {
        return null;
    }

    @Override
    public Route findById(Long id) {
        return null;
    }

    @Override
    public Route findRouteByStations(String from, String to) {
        return null;
    }

    @Override
    public Route create(Route route) {
        return null;
    }

    @Override
    public Route update(Route route) {
        return null;
    }

    @Override
    public void delete(Route route) {

    }
}
