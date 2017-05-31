package service;

import dao.AbstractDAOFactory;
import dao.DAOFactory;
import dao.DataBase;
import model.entity.Route;
import model.entity.Station;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class StationService {
    private static final Log LOG = LogFactory.getLog(StationService.class);
    private static final DataBase DB = DataBase.MYSQL;
    private static StationService INSTANCE;

    private DAOFactory factory;

    private StationService(){
        factory = AbstractDAOFactory.createDAOFactory(DB);
    }

    public static StationService getInstance(){
        if(INSTANCE == null){
            synchronized (StationService.class){
                if (INSTANCE == null){
                    INSTANCE = new StationService();
                }
            }
        }

        return INSTANCE;
    }

    public Station findFromStation(Route route){
        return factory.createStationDAO().findById(route.getFrom_id());
    }

    public Station findToStation(Route route){
        return factory.createStationDAO().findById(route.getTo_id());
    }
}
