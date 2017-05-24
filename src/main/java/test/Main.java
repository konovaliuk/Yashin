package test;

import dao.*;
import entity.Price;
import entity.Request;
import entity.Route;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        DAOFactory factory = AbstractDAOFactory.createDAOFactory(DataBase.MYSQL);

        PriceDAO priceDAO = factory.createPriceDAO();
        RequestDAO requestDAO = factory.createRequestDAO();
        RouteDAO routeDAO = factory.createRouteDAO();
        StationDAO stationDAO = factory.createStationDAO();
        TrainDAO trainDAO = factory.createTrainDAO();
        UserDAO userDAO = factory.createUserDAO();

        System.out.println(priceDAO.findAll());
        System.out.println(priceDAO.findById(1l));
        Price price = priceDAO.findById(1l);
        price.setBerth_factor(33d);
        System.out.println(priceDAO.update(price));
        price.setId(null);
        price.setDeluxe_factor(3333d);
        price = priceDAO.create(price);
        System.out.println(price);
        priceDAO.delete(price);

        System.out.println(requestDAO.findAll());
        System.out.println(requestDAO.findById(1l));
        System.out.println(requestDAO.findByTrain(1l));
        System.out.println(requestDAO.findByUser(1l));

        Request request = requestDAO.findById(1l);
        request.setPrice(3000l);
        System.out.println(requestDAO.update(request));
        request.setId(null);
        request.setTrain_id(1L);
        request = requestDAO.create(request);
        System.out.println(request);
        requestDAO.delete(request);
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

    }
}
