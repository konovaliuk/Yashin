package service;

import dao.AbstractDAOFactory;
import dao.DAOFactory;
import dao.DataBase;
import dao.RequestDAO;
import dao.mysql.TypePlace;
import dto.Ticket;
import exception.InvalidDataBaseOperation;
import model.entity.Request;
import model.entity.Route;
import model.entity.Train;
import model.entity.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class RequestService {
    private static final Logger LOG = Logger.getLogger(RequestDAO.class.getName());
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

    public Request addRequest(Request request) throws InvalidDataBaseOperation{
        TypePlace place = request.getType();
        Train train = factory.createTrainDAO().findById(request.getTrainId());

        switch (place){
            case B:{
                if (train.getBerthFree() == 0){
                    throw new InvalidDataBaseOperation("Someone booked all tickets to this train." +
                            " Please, go to the main page and select other train");
                }
                TrainService.getInstance().reserveBerthPlace(train);
                break;
            }
            case C:{
                if (train.getCompartmentFree() == 0){
                    throw new InvalidDataBaseOperation("Someone booked all tickets to this train." +
                            " Please, go to the main page and select other train");
                }
                TrainService.getInstance().reserveCompartmentPlace(train);
                break;
            }
            case L:{
                if (train.getDeluxeFree() == 0){
                    throw new InvalidDataBaseOperation("Someone booked all tickets to this train." +
                            " Please, go to the main page and select other train");
                }
                TrainService.getInstance().reserveDeluxePlace(train);
                break;
            }
        }

        return factory.createRequestDAO().create(request);
    }

    public void reserveTickets(final List<Ticket> tickets) throws InvalidDataBaseOperation{
        for (Ticket ticket: tickets){
            Request request = new Request.RequestBuilder()
                    .setPrice(ticket.getPrice())
                    .setType(TypePlace.valueOf(ticket.getTypePlace()))
                    .setUserId(ticket.getUserId())
                    .setTrainId(ticket.getTrainId())
                    .build();

            ticket.setRequestId(addRequest(request).getId());
        }
    }

    public List<Ticket> findAllTickets(){
        List<Request> requests = factory.createRequestDAO().findAll();
        List<Ticket> result = new ArrayList<>();
        for(Request request: requests){
            Train train = factory.createTrainDAO().findById(request.getTrainId());
            Route route = factory.createRouteDAO().findById(train.getRouteId());
            User user = factory.createUserDAO().findById(request.getUserId());

            Ticket ticket = new Ticket();
            ticket.setTrainId(train.getId());
            ticket.setRequestId(request.getId());
            ticket.setUserId(request.getUserId());

            ticket.setFromCity(factory.createStationDAO().findById(route.getFromId()).getName());
            ticket.setToCity(factory.createStationDAO().findById(route.getToId()).getName());

            ticket.setFromDate(TrainService.getInstance().formatDate(route.getFromTime()));
            ticket.setToDate(TrainService.getInstance().formatDate(route.getToTime()));

            ticket.setName(user.getName());
            ticket.setSurname(user.getSurname());

            ticket.setTypePlace(request.getType().toString());
            ticket.setPrice(request.getPrice());
            ticket.setUserId(user.getId());
            result.add(ticket);
        }

        result.sort(new Comparator<Ticket>() {
            @Override
            public int compare(Ticket o1, Ticket o2) {
                if(o1.getRequestId() > o2.getRequestId()) return -1;
                else if(o1.getRequestId() < o2.getRequestId()) return 1;
                else return 0;
            }
        });
        return result;
    }

    public void cancelRequest(Ticket ticket){
        Train train = TrainService.getInstance().findTrainById(ticket.getTrainId());
        switch (ticket.getTypePlace()){
            case "C": TrainService.getInstance().cancelCompartmentPlace(train); break;
            case "L": TrainService.getInstance().cancelDeluxePlace(train); break;
            default: TrainService.getInstance().cancelBerthPlace(train); break;
        }
        Request request = factory.createRequestDAO().findById(ticket.getRequestId());
        factory.createRequestDAO().delete(request);
    }
}
