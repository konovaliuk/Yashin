package command.user;

import command.Command;
import dto.Ticket;
import dto.TrainRoute;
import model.entity.Route;
import model.entity.User;
import service.RouteService;
import service.TrainService;
import util.Configuration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static command.user.CommandUserAdmin.USERNAME_ATTRIBUTE;
import static command.user.CommandUserAdmin.USER_ATTRIBUTE;

public class MakeTicketsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User userNow = (User) request.getSession(false).getAttribute(USER_ATTRIBUTE);
        if(userNow == null)
            return Configuration.getInstance().getConfig(Configuration.LOGIN);

        String page = Configuration.getInstance().getConfig(Configuration.ORDER);
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
            String parameter = request.getParameter("train" + trainRoute.getTrainId());
            if (!parameter.equals("none")) {
                Ticket ticket = new Ticket();
                ticket.setTrainId(trainRoute.getTrainId());

                ticket.setFromCity(trainRoute.getFromCity());
                ticket.setToCity(trainRoute.getToCity());

                ticket.setFromDate(trainRoute.getFromDate());
                ticket.setToDate(trainRoute.getToDate());

                User user = (User) request.getSession().getAttribute("user");
                ticket.setName(user.getName());
                ticket.setSurname(user.getSurname());

                Double price;
                Long max;
                Route route = RouteService.getInstance().findRouteById(trainRoute.getRouteId());
                switch (parameter){
                    case "C": {
                        max = trainRoute.getCompartmentFree();
                        price = RouteService.getInstance().findCompartmentPrice(route);
                        break;
                    }
                    case "L": {
                        max = trainRoute.getDeluxeFree();
                        price = RouteService.getInstance().findDeluxePrice(route);
                        break;
                    }
                    default: {
                        max = trainRoute.getBerthFree();
                        price = RouteService.getInstance().findBerthPrice(route);
                        break;
                    }
                }
                ticket.setMax(max);
                ticket.setTypePlace(parameter);
                ticket.setPrice(price);
                ticket.setUserId(user.getId());
                tickets.add(ticket);
            }

        }

        if (tickets.isEmpty()){
            request.setAttribute("noTickets", true);
        } else{
            request.setAttribute("tickets", tickets);
            request.getSession(false).setAttribute("tickets", tickets);
        }
        request.setAttribute(USERNAME_ATTRIBUTE, userNow.getName());
        return page;
    }
}
