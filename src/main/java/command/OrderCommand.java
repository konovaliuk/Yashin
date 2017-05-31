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
        for(TrainRoute trainRoute: trains){
            String parameter = request.getParameter("train" + trainRoute.getTrain_id());
            if(!parameter.equals("none")){
                Ticket ticket = new Ticket();
                ticket.setTrain_id(trainRoute.getTrain_id());

                ticket.setFromCity(trainRoute.getFromCity());
                ticket.setToCity(trainRoute.getToCity());

                ticket.setFromDate(trainRoute.getFromDate());
                ticket.setToDate(trainRoute.getToDate());

                User user = (User) request.getSession().getAttribute("user");
                ticket.setName(user.getName());
                ticket.setSurname(user.getSurname());

                ticket.setTypePlace(parameter);
                Double price;
                Route route = RouteService.getInstance().findRouteById(trainRoute.getRoute_id());
                switch (parameter){
                    case "C": {
                        price = RouteService.getInstance().findCompartmentPrice(route);
                        break;
                    }
                    case "L": {
                        price = RouteService.getInstance().findDeluxePrice(route);
                        break;
                    }
                    default: {
                        price = RouteService.getInstance().findBerthPrice(route);
                        break;
                    }
                }
                ticket.setPrice(price);

                tickets.add(ticket);
            }

        }

        request.setAttribute("tickets", tickets);
        return page;
    }
}
