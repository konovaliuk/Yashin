package service;

import dao.AbstractDAOFactory;
import dao.DAOFactory;
import dao.DataBase;
import model.entity.Price;
import model.entity.Route;
import model.entity.Station;
import model.entity.Train;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class RouteService {
    private static final Log LOG = LogFactory.getLog(RouteService.class);
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
    public Route getRouteByTrain(Train train){
        return factory.createRouteDAO().findById(train.getRoute_id());
    }

    public List<Station> getAvailableFromStations(){
        List<Route> routes = factory.createRouteDAO().findAll();
        Set<Station> stations = new HashSet<>();

        for (Route route: routes){
            Station station = factory.createStationDAO().findById(route.getFrom_id());
            stations.add(station);
        }

        return new ArrayList<>(stations);
    }

    public List<Station> getAvailableToStations(Station fromStation){
        List<Route> routes = factory.createRouteDAO().findAll();
        Set<Station> stations = new HashSet<>();

        for (Route route: routes){
            Station fromStationAll = factory.createStationDAO().findById(route.getFrom_id());

            if(fromStation.getId().equals(fromStationAll.getId())){
                Station toStation = factory.createStationDAO().findById(route.getTo_id());
                stations.add(toStation);
            }
        }

        return new ArrayList<>(stations);
    }

    public List<Route> findRouteByStations(Station from, Station to){
        List<Route> routes = factory.createRouteDAO().findByFromId(from.getId());
        List<Route> result = new ArrayList<>();

        for(Route route: routes){
            if (route.getTo_id().equals(to.getId())){
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
                if (format.parse(route.getFrom_time()).getTime() > time) {
                    result.add(route);
                }
            }
        }catch (ParseException e){
            LOG.error(e.getMessage());
            e.printStackTrace();
        }

        return result;
    }

    public Double getCompartmentPrice(Route route){
        Price compartment = factory.createPriceDAO().findById(route.getPrice_id());
        return compartment.getCompartment_factor() * route.getDistance();
    }


    public Double getBerthPrice(Route route){
        Price compartment = factory.createPriceDAO().findById(route.getPrice_id());
        return compartment.getBerth_factor() * route.getDistance();
    }


    public Double getDeluxePrice(Route route){
        Price compartment = factory.createPriceDAO().findById(route.getPrice_id());
        return compartment.getDeluxe_factor() * route.getDistance();
    }
}
