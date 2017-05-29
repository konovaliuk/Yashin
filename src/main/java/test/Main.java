package test;

import dao.*;
import dao.mysql.TypePlace;
import model.entity.*;
import service.LoginService;
import service.RequestService;
import service.RouteService;
import service.TrainService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        DAOFactory factory = AbstractDAOFactory.createDAOFactory(DataBase.MYSQL);
//
//        PriceDAO priceDAO = factory.createPriceDAO();
//        RequestDAO requestDAO = factory.createRequestDAO();
//        RouteDAO routeDAO = factory.createRouteDAO();
//        StationDAO stationDAO = factory.createStationDAO();
//        TrainDAO trainDAO = factory.createTrainDAO();
//        UserDAO userDAO = factory.createUserDAO();
//
//        System.out.println(priceDAO.findAll());
//        System.out.println(priceDAO.findById(1l));
//        Price price = priceDAO.findById(1l);
//        price.setBerth_factor(33d);
//        System.out.println(priceDAO.update(price));
//        price.setId(null);
//        price.setDeluxe_factor(3333d);
//        price = priceDAO.create(price);
//        System.out.println(price);
//        priceDAO.delete(price);
//
//        System.out.println(requestDAO.findAll());
//        System.out.println(requestDAO.findById(1l));
//        System.out.println(requestDAO.findByTrain(1l));
//        System.out.println(requestDAO.findByUser(1l));
//
//        Request request = requestDAO.findById(1l);
//        request.setPrice(3000l);
//        System.out.println(requestDAO.update(request));
//        request.setId(null);
//        request.setTrain_id(1L);
//        request = requestDAO.create(request);
//        System.out.println(request);
//        requestDAO.delete(request);
//
//        System.out.println(routeDAO.findAll());
//        System.out.println(routeDAO.findByFromId(1l));
//        System.out.println(routeDAO.findByToId(1l));
//        System.out.println(routeDAO.findById(1l));
//
//        System.out.println(stationDAO.findAll());
//        System.out.println(stationDAO.findById(1l));
//
//        System.out.println(trainDAO.findAll());
//        System.out.println(trainDAO.findById(1l));
//        System.out.println(trainDAO.findByRoute(1l));
//
//        System.out.println(userDAO.findAll());
//        System.out.println(userDAO.findAdmins());
//        System.out.println(userDAO.findUsers());
//        System.out.println(userDAO.findByEmail("root"));
//        System.out.println(userDAO.findById(2l));
//
//
//        List<Station> fromStations = RouteService.getInstance().getAvailableFromStations();
//        System.out.println(fromStations);
//
//        Station from = fromStations.get(2);
//        List<Station> toStations = RouteService.getInstance().getAvailableToStations(from);
//        Station to = toStations.get(0);
//
//        System.out.println(to);
//
//        List<Route> routes = RouteService.getInstance().findRouteByStations(from, to);
//        System.out.println(routes);
//
//        List<Route> routes1 = RouteService.getInstance().findRoutesFromTime(routes, new Date(2016-1900, 5, 7));
//
//        List<Train> trains = TrainService.getInstance().getTrainsForRoutes(routes1);
//        System.out.println(trains);
//
//        Train train = trains.get(0);
//        Route route = RouteService.getInstance().getRouteByTrain(train);
//
//        System.out.println(route);
//
//
//        System.out.println(RouteService.getInstance().getBerthPrice(route));
//        System.out.println(RouteService.getInstance().getCompartmentPrice(route));
//        System.out.println(RouteService.getInstance().getDeluxePrice(route));
//
//
//        Request request = new Request();
//        request.setUser_id(LoginService.getInstance().isPresentLogin("andy97@ukr.net").getId());
//        request.setTrain_id(train.getId());
//        request.setType(TypePlace.B);
//        request.setPrice(RouteService.getInstance().getBerthPrice(route));
//
//        RequestService.getInstance().addRequest(request);

    }
}
