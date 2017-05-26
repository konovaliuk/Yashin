package service;

import dao.AbstractDAOFactory;
import dao.DAOFactory;
import dao.DataBase;
import model.entity.Route;
import model.entity.Station;
import model.entity.Train;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

public class TrainService {
    private static final Log LOG = LogFactory.getLog(TrainService.class);
    private static final DataBase DB = DataBase.MYSQL;
    private static final TrainService INSTANCE = new TrainService();


    private DAOFactory factory;

    private TrainService(){
        factory = AbstractDAOFactory.createDAOFactory(DB);
    }

    public static TrainService getInstance(){
        return INSTANCE;
    }

    public List<Train> getTrainsForRoutes(List<Route> routes){
        List<Train> result = new ArrayList<>();

        for (Route route: routes){
            result.addAll(factory.createTrainDAO().findByRoute(route.getId()));
        }

        return result;

    }

    public Train reserveCompartmentPlace(Train train){
        train.setCompartment_free(train.getCompartment_free() - 1);
        return factory.createTrainDAO().update(train);
    }


    public Train reserveBerthPlace(Train train){
        train.setCompartment_free(train.getBerth_free() - 1);
        return factory.createTrainDAO().update(train);
    }


    public Train reserveDeluxePlace(Train train){
        train.setCompartment_free(train.getDeluxe_free() - 1);
        return factory.createTrainDAO().update(train);
    }



}
