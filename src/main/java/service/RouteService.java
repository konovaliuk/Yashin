package service;

import dao.AbstractDAOFactory;
import dao.DAOFactory;
import dao.DataBase;
import model.entity.Price;
import model.entity.Route;
import model.entity.Station;
import model.entity.Train;
import java.util.logging.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class RouteService {
    private static final Logger LOG = Logger.getLogger(RouteService.class.getName());
    private static final DataBase DB = DataBase.MYSQL;
    private static RouteService INSTANCE;

    private DAOFactory factory;

    private RouteService(){
        factory = AbstractDAOFactory.createDAOFactory(DB);
    }

    public static RouteService getInstance(){
        if(INSTANCE == null){
            synchronized (RouteService.class){
                if (INSTANCE == null){
                    INSTANCE = new RouteService();
                }
            }
        }

        return INSTANCE;
    }

    public Route findRouteById(Long id){
        return factory.createRouteDAO().findById(id);
    }

    public Route findRouteByTrain(Train train){
        return factory.createRouteDAO().findById(train.getRouteId());
    }

    public List<Station> findAvailableFromStations(){
        List<Route> routes = factory.createRouteDAO().findAll();
        Set<Station> stations = new HashSet<>();

        for (Route route: routes){
            Station station = factory.createStationDAO().findById(route.getFromId());
            stations.add(station);
        }

        return new ArrayList<>(stations);
    }

    public List<Station> findAvailableToStations(){
        List<Route> routes = factory.createRouteDAO().findAll();
        Set<Station> stations = new HashSet<>();

        for (Route route: routes){
            Station station = factory.createStationDAO().findById(route.getToId());
            stations.add(station);
        }

        return new ArrayList<>(stations);
    }

    public List<Route> findRouteByStations(Station from, Station to){
        List<Route> routes = factory.createRouteDAO().findByFromId(from.getId());
        List<Route> result = new ArrayList<>();

        for(Route route: routes){
            if (route.getToId().equals(to.getId())){
                result.add(route);
            }
        }

        return result;
    }

    public List<Route> findRoutesFromTime(List<Route> routes, Date date){
        long time = date.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");

        List<Route> result = new ArrayList<>();
        try {
            for (Route route : routes) {
                if (format.parse(route.getFromTime()).getTime() > time) {
                    result.add(route);
                }
            }
        }catch (ParseException e){
            LOG.severe(e.getMessage());
            e.printStackTrace();
        }

        return result;
    }

    public Station findFromStation(Train train){
        Route route = findRouteByTrain(train);
        return StationService.getInstance().findFromStation(route);
    }


    public Station findToStation(Train train){
        Route route = findRouteByTrain(train);
        return StationService.getInstance().findToStation(route);
    }

    public Double findCompartmentPrice(Route route){
        Price compartment = factory.createPriceDAO().findById(route.getPriceId());
        return compartment.getCompartmentFactor() * route.getDistance();
    }


    public Double findBerthPrice(Route route){
        Price compartment = factory.createPriceDAO().findById(route.getPriceId());
        return compartment.getBerthFactor() * route.getDistance();
    }


    public Double findDeluxePrice(Route route){
        Price compartment = factory.createPriceDAO().findById(route.getPriceId());
        return compartment.getDeluxeFactor() * route.getDistance();
    }
}
