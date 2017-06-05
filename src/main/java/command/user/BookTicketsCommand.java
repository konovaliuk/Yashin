package command.user;

import command.Command;
import dto.Ticket;
import exception.InvalidDataBaseOperation;
import model.entity.User;
import service.RequestService;
import service.RouteService;
import util.Configuration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static command.user.CommandUserAdmin.USERNAME_ATTRIBUTE;
import static command.user.CommandUserAdmin.USER_ATTRIBUTE;

public class BookTicketsCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User userNow = (User) request.getSession(false).getAttribute(USER_ATTRIBUTE);
        if(userNow == null)
            return Configuration.getInstance().getConfig(Configuration.LOGIN);

        String page = Configuration.getInstance().getConfig(Configuration.TICKET);

        List<Ticket> tickets = (List<Ticket>) request.getSession(false).getAttribute("tickets");
        if(tickets == null){
            request.setAttribute("cityFrom", RouteService.getInstance().findAvailableFromStations());
            request.setAttribute("cityTo", RouteService.getInstance().findAvailableToStations());
            request.setAttribute("trains", null);

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            request.setAttribute("d", format.format(new Date()));
            page = Configuration.getInstance().getConfig(Configuration.DATE);
        } else {
            List<Ticket> resultTickets = new ArrayList<>();

            for (Ticket ticket : tickets) {
                Integer count = Integer.parseInt(request.getParameter(ticket.getTrainId().toString()));
                resultTickets.addAll(addTickets(ticket, count));
            }

            try {
                RequestService.getInstance().reserveTickets(resultTickets);
                request.setAttribute("tickets", resultTickets);
            } catch (InvalidDataBaseOperation e){
                request.setAttribute("messageError", e.getMessage());
                page = Configuration.getInstance().getConfig(Configuration.ERROR);
            }

        }
        request.setAttribute(USERNAME_ATTRIBUTE, userNow.getName());
        return page;
    }

    private List<Ticket> addTickets(Ticket ticket, Integer count){
        List<Ticket> result = new ArrayList<>();
        for (int i = 0; i < count; i++){
            Ticket ticket1 = new Ticket();
            ticket1.setRequestId(ticket.getRequestId());
            ticket1.setUserId(ticket.getUserId());
            ticket1.setPrice(ticket.getPrice());
            ticket1.setTypePlace(ticket.getTypePlace());
            ticket1.setSurname(ticket.getSurname());
            ticket1.setName(ticket.getName());
            ticket1.setFromDate(ticket.getFromDate());
            ticket1.setToDate(ticket.getToDate());
            ticket1.setFromCity(ticket.getFromCity());
            ticket1.setToCity(ticket.getToCity());
            ticket1.setMax(ticket.getMax());
            ticket1.setTrainId(ticket.getTrainId());
            result.add(ticket1);
        }

        return result;
    }
}