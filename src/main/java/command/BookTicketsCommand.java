package command;

import dto.Ticket;
import dto.TrainRoute;
import model.entity.Route;
import model.entity.User;
import service.RequestService;
import service.RouteService;
import service.TrainService;
import util.Config;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class BookTicketsCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            return Config.getInstance().getConfig(Config.LOGIN);
        }
        String page = Config.getInstance().getConfig(Config.TICKET);

        List<Ticket> tickets = (List<Ticket>) request.getSession(false).getAttribute("tickets");
        List<Ticket> resultTickets = new ArrayList<>();

        for(Ticket ticket: tickets){
            Integer count = Integer.parseInt(request.getParameter(ticket.getTrain_id().toString()));
            resultTickets.addAll(addTickets(ticket, count));
        }

        RequestService.getInstance().reserveTickets(resultTickets);
        request.setAttribute("tickets", resultTickets);
        return page;
    }

    private List<Ticket> addTickets(Ticket ticket, Integer count){
        List<Ticket> result = new ArrayList<>();
        for (int i = 0; i < count; i++){
            Ticket ticket1 = new Ticket();
            ticket1.setRequest_id(ticket.getRequest_id());
            ticket1.setUser_id(ticket.getUser_id());
            ticket1.setPrice(ticket.getPrice());
            ticket1.setTypePlace(ticket.getTypePlace());
            ticket1.setSurname(ticket.getSurname());
            ticket1.setName(ticket.getName());
            ticket1.setFromDate(ticket.getFromDate());
            ticket1.setToDate(ticket.getToDate());
            ticket1.setFromCity(ticket.getFromCity());
            ticket1.setToCity(ticket.getToCity());
            ticket1.setMax(ticket.getMax());
            ticket1.setTrain_id(ticket.getTrain_id());
            result.add(ticket1);
        }

        return result;
    }
}