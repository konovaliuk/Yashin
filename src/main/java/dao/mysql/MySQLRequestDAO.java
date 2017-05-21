package dao.mysql;

import dao.RequestDAO;
import entity.Request;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

class MySQLRequestDAO implements RequestDAO{
    private static final Log LOG = LogFactory.getLog(MySQLPriceDAO.class);
    private static final MySQLRequestDAO INSTANCE = new MySQLRequestDAO();

    private MySQLRequestDAO(){}

    public static MySQLRequestDAO getInstance(){
        return INSTANCE;
    }

    @Override
    public List<Request> findAll() {
        return null;
    }

    @Override
    public List<Request> findByTrain(Long id) {
        return null;
    }

    @Override
    public List<Request> findByRoute(Long id) {
        return null;
    }

    @Override
    public Request findById(Long id) {
        return null;
    }

    @Override
    public Double findPrice(Long id) {
        return null;
    }

    @Override
    public Request create(Request request) {
        return null;
    }

    @Override
    public Request update(Request request) {
        return null;
    }

    @Override
    public void delete(Request request) {

    }
}
