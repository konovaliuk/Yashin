package service;

import dao.AbstractDAOFactory;
import dao.DAOFactory;
import dao.DataBase;
import dao.mysql.TypePlace;
import model.entity.Request;
import model.entity.Train;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RequestService {
    private static final Log LOG = LogFactory.getLog(RequestService.class);
    private static final DataBase DB = DataBase.MYSQL;
    private static RequestService INSTANCE;

    private DAOFactory factory;

    private RequestService(){
        factory = AbstractDAOFactory.createDAOFactory(DB);
    }

    public static RequestService getInstance(){
        if(INSTANCE == null){
            synchronized (RequestService.class){
                if (INSTANCE == null){
                    INSTANCE = new RequestService();
                }
            }
        }

        return INSTANCE;
    }

    public Request addRequest(Request request){
        TypePlace place = request.getType();
        Train train = factory.createTrainDAO().findById(request.getTrain_id());

        switch (place){
            case B:{
                train.setBerth_free(train.getBerth_free() - 1);
                TrainService.getInstance().reserveBerthPlace(train);
                break;
            }
            case C:{
                train.setCompartment_free(train.getCompartment_free() - 1);
                TrainService.getInstance().reserveCompartmentPlace(train);
                break;
            }
            case L:{
                train.setDeluxe_free(train.getDeluxe_free() - 1);
                TrainService.getInstance().reserveDeluxePlace(train);
                break;
            }
        }

        return factory.createRequestDAO().create(request);
    }
}
