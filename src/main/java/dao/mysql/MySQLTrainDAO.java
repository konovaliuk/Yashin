package dao.mysql;

import dao.TrainDAO;
import entity.Train;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;
import java.util.List;

class MySQLTrainDAO implements TrainDAO{
    private static final Log LOG = LogFactory.getLog(MySQLTrainDAO.class);
    private static final MySQLTrainDAO INSTANCE = new MySQLTrainDAO();

    private MySQLTrainDAO(){}

    public static MySQLTrainDAO getInstance(){
        return INSTANCE;
    }

    @Override
    public List<Train> findAll() {
        return null;
    }

    @Override
    public List<Train> findByRoute(Long route_id) {
        return null;
    }

    @Override
    public List<Train> findByDate(Date date) {
        return null;
    }

    @Override
    public Train findById(Long id) {
        return null;
    }

    @Override
    public Integer findCompartmentSize(Long id) {
        return null;
    }

    @Override
    public Integer findDeluxeSize(Long id) {
        return null;
    }

    @Override
    public Integer findBerthSize(Long id) {
        return null;
    }

    @Override
    public Train create(Train train) {
        return null;
    }

    @Override
    public Train update(Train train) {
        return null;
    }

    @Override
    public void delete(Train train) {

    }
}
