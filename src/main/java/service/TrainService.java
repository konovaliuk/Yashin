package service;

import dao.AbstractDAOFactory;
import dao.DAOFactory;
import dao.DataBase;
import dao.StationDAO;
import dto.TrainRoute;
import model.entity.Route;
import model.entity.Station;
import model.entity.Train;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TrainService {
    private static final Log LOG = LogFactory.getLog(TrainService.class);
    private static final DataBase DB = DataBase.MYSQL;
    private static TrainService INSTANCE;

    private DAOFactory factory;

    private TrainService(){
        factory = AbstractDAOFactory.createDAOFactory(DB);
    }

    public static TrainService getInstance(){
        if(INSTANCE == null){
            synchronized (TrainService.class){
                if (INSTANCE == null){
                    INSTANCE = new TrainService();
                }
            }
        }

        return INSTANCE;
    }
    public List<Train> getTrainsForRoutes(List<Route> routes){
        List<Train> result = new ArrayList<>();

        for (Route route: routes){
            result.addAll(factory.createTrainDAO().findByRoute(route.getId()));
        }

        return result;
    }


    public List<TrainRoute> findTrainsAndRoutes(Long fromId, Long toId, Date fromDate){
        Station from = factory.createStationDAO().findById(fromId);
        Station to = factory.createStationDAO().findById(toId);

        List<Route> routes = RouteService.getInstance().findRouteByStations(from, to);
        routes = RouteService.getInstance().findRoutesFromTime(routes, fromDate);
        List<Train> trains = TrainService.getInstance().getTrainsForRoutes(routes);

        List<TrainRoute> trainRoutes = new ArrayList<>();
        for(Train train: trains){
            Route route = RouteService.getInstance().findRouteByTrain(train);

            TrainRoute trainRoute = new TrainRoute();
            trainRoute.setRoute_id(route.getId());
            trainRoute.setTrain_id(train.getId());

            trainRoute.setFromCity(StationService.getInstance().findFromStation(route).getName());
            trainRoute.setToCity(StationService.getInstance().findToStation(route).getName());

            trainRoute.setFromDate(formatDate(route.getFrom_time()));
            trainRoute.setToDate(formatDate(route.getTo_time()));

            trainRoute.setDistance(route.getDistance());

            trainRoute.setBerth_free(train.getBerth_free());
            trainRoute.setCompartment_free(train.getCompartment_free());
            trainRoute.setDeluxe_free(train.getDeluxe_free());

            trainRoute.setBerth_price(RouteService.getInstance().findBerthPrice(route));
            trainRoute.setCompartment_price(RouteService.getInstance().findCompartmentPrice(route));
            trainRoute.setDeluxe_price(RouteService.getInstance().findDeluxePrice(route));

            trainRoutes.add(trainRoute);
        }

        return trainRoutes;
    }

    private String formatDate(String date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
        Date result = null;
        try {
            result = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(result);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);

        String resultDate = String.format("%02d.%02d.%04d\n%02d:%02d",
                day, month, year, hour, minutes);

        return resultDate;

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
