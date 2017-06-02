package command;

import dto.Ticket;
import dto.TrainRoute;
import model.entity.Route;
import model.entity.User;
import service.RouteService;
import service.TrainService;
import util.Config;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            return Config.getInstance().getConfig(Config.LOGIN);
        }

        String page = Config.getInstance().getConfig(Config.ORDER);
        Long from_id = Long.parseLong(request.getParameter("from"));
        Long to_id = Long.parseLong(request.getParameter("to"));
        Integer time = Integer.parseInt(request.getParameter("time"));
        String dateString = request.getParameter("date");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;
        try {
            date = format.parse(dateString);
            date.setHours(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<TrainRoute> trains = TrainService.getInstance().findTrainsAndRoutes(from_id, to_id, date);
        List<Ticket> tickets = new ArrayList<>();
        for (TrainRoute trainRoute : trains) {
            String parameter = request.getParameter("train" + trainRoute.getTrain_id());
            if (!parameter.equals("none")) {
                Ticket ticket = new Ticket();
                ticket.setTrain_id(trainRoute.getTrain_id());

                ticket.setFromCity(trainRoute.getFromCity());
                ticket.setToCity(trainRoute.getToCity());

                ticket.setFromDate(trainRoute.getFromDate());
                ticket.setToDate(trainRoute.getToDate());

                User user = (User) request.getSession().getAttribute("user");
                ticket.setName(user.getName());
                ticket.setSurname(user.getSurname());

                Double price;
                Long max;
                Route route = RouteService.getInstance().findRouteById(trainRoute.getRoute_id());
                switch (parameter){
                    case "C": {
                        max = trainRoute.getCompartment_free();
                        price = RouteService.getInstance().findCompartmentPrice(route);
                        break;
                    }
                    case "L": {
                        max = trainRoute.getDeluxe_free();
                        price = RouteService.getInstance().findDeluxePrice(route);
                        break;
                    }
                    default: {
                        max = trainRoute.getBerth_free();
                        price = RouteService.getInstance().findBerthPrice(route);
                        break;
                    }
                }
                ticket.setMax(max);
                ticket.setTypePlace(parameter);
                ticket.setPrice(price);
                ticket.setUser_id(user.getId());
                tickets.add(ticket);
            }

        }

        if (tickets.isEmpty()){
            request.setAttribute("noTickets", true);
        } else{
            request.setAttribute("tickets", tickets);
            request.getSession(false).setAttribute("tickets", tickets);
        }
        return page;
    }
}
